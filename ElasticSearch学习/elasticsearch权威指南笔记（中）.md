## Elasticsearch权威指南笔记（中）

### 十一、请求体查询

#### 1. 请求体查询
+ 简单查询语句(lite)是一种有效的命令行adhoc查询。更复杂的查询，必须使用请求体查询(request bodysearch )API
+ 请求体查询中的空查询
    1. 返回索引中所有文档(`hits`中默认显示10个)
    ```
    GET /_search
    {}
    ```
    2. 同字符串查询一样，可以查询一个，多个或`_all`索引(indices)或类型(types)
    ```
    GET /index_2014*/type1,type2/_search
    {}
    ```
    3. 可以使用`from`及`size`参数进行分页
    ```
    GET /_search
    {
      "from": 30,
      "size": 10
    }
    ```
+ Elasticsearch的作者们倾向于使用`GET`提交查询请求，因为他们觉得这个词相比`POST`来说，能更好的描述这种行为
+ 然而，因为携带交互数据的`GET`请求并不被广泛支持，所以search API同样支持`POST`请求，这个原理同样应用于其他携带交互数据的GET API请求中。
+ 相对于神秘的查询字符串方法，请求体查询允许我们使用结构化查询Query DSL(Query Domain Specific Language)

#### 2. 结构化查询
+ 结构化查询是一种灵活的，多表现形式的查询语言，Elasticsearch在一个简单的JSON接口中用结构化查询来展现Lucene绝大多数能力
+ 使用结构化查询，你需要传递`query`参数
```
GET /_search
{
  "query": YOUR_QUERY_HERE
}
```
+ 空查询在功能上等同于使用`match_all`查询子句，匹配所有的文档
```
GET /_search
{
  "query": {
    "match_all": {}
  }
}
```
+ 查询子句一般结构及示例
```
{
  QUERY_NAME: {
    ARGUMENT: VALUE,
    ARGUMENT: VALUE,...
  }
}
{
  QUERY_NAME: {
    FIELD_NAME: {
      ARGUMENT: VALUE,
      ARGUMENT: VALUE,...
    }
  }
}
```
```
GET /_search
{
  "query": {
    "match": {
      "interests": "music"
    }
  }
}
```
+ 合并多子句
    + 查询子句就像是搭积木一样，可以合并简单的子句为一个复杂的查询语句
    + 简单子句(leaf clauses)(比如`match`子句)用以在将查询字符串与一个字段(或多字段)进行比较
    + 复合子句(compound)用以合并其他的子句。例如，`bool`子句允许你合并其他的合法子句，例`must` `must_not` `should`
    ```
    {
      "bool": {
        "must": { "match": { "tweet": "elasticsearch" }},
        "must_not": { "match": { "name": "mary" }},
        "should": { "match": { "tweet": "full text" }}
      }
    }
    ```
    + 复合子句能合并任意其他查询子句，包括其他的复合子句。这就意味着复合子句可以相互嵌套，从而实现非常复杂的逻辑
    + 以下实例查询在inbox中未标记spam或星标(starred)中找出包含"business opportunity"的邮件
    ```
    {
      "bool": {
        "must": { "match": { "email": "business opportunity" }},
        "should": [
          { "match": { "starred": true }},
          { "bool": {
            "must": { "folder": "inbox" }},
            "must_not": { "spam": true }}
          }}
        ],
        "minimum_should_match": 1
      }
    }
    ```

#### 3. 查询与过滤
+ 两种结构化语句：结构化查询(Query DSL)和结构化过滤(Filter DSL)
+ 过滤语句会询问每个文档的字段值是否包含着特定值
+ 查询语句与过滤语句相似，但问法不同，查询语句会询问每个文档的字段值与特定值的匹配程度如何
+ 原则上来说，使用查询语句做全文本搜索或其他需要进行相关性评分的时候，剩下的全部用过滤语句
+ 查询语句和过滤语句可以放在各自的上下文中，在ElasticSearch API中我们会看到许多带有`query`或`filter`的语句
+ 这些语句既可以包含单条`query`语句，也可以包含一条`filter`子句。这些语句需要首先创建一个`query`或`filter`的上下文关系
+ 复合查询语句可以加入其他查询子句，复合过滤语句也可以加入其他过滤子句。通常情况下，一条查询语句需要过滤语句的辅助，全文本搜索除外
+ 查询语句可以包含过滤子句，反之亦然。以便于我们切换`query`或`filter`的上下文。这就要求我们在读懂需求的同时构造正确有效的语句

#### 4. 最重要的查询过滤语句
1. `term`过滤  
`term`主要用于精确匹配哪些值，比如数字、日期、布尔值或`not_analyzed`的字符串(未经分析的文本数据类型)
```
{ "term": { "age": 26 }}
{ "term": { "date": "2014-09-01" }}
{ "term": { "public": true }}
{ "term": { "tag": "full_text" }}
```
2. `terms`过滤  
`terms`跟`term`有点类似，但`terms`允许指定多个匹配条件。如果某个字段指定了多个值，那么文档需要一起去做匹配
```
{
  "terms": {
    "tag": [ "search", "full_text", "nosql" ]
  }
}
```
3. `range`过滤  
`range`过滤允许我们按照指定范围查找一批数据 `gt`大于 `gte`大于等于 `lt`小于 `lte`小于等于
```
{
  "range": {
    "age": {
      "gte": 20,
      "lt": 30
    }
  }
}
```
4. `exists`和`missing`过滤  
`exists`和`missing`过滤可以用于查找文档中是否包含指定字段或没有某个字段，类似于SQL语句中的`IS_NULL`条件  
这两个过滤只是针对已经查出一批数据来，但是想区分出某个字段是否存在的时候使用
```
{
  "exists": {
    "field": "title"
  }
}
```
5. `bool`过滤  
`bool`过滤可以用来合并多个过滤条件查询结果的布尔逻辑，它包含以下操作符
    + `must`多个查询条件的完全匹配，相当于and
    + `must_not`多个查询条件的相反匹配，相当于not
    + `should`至少有一个查询条件匹配，相当于or
    + 这些参数可以分别继承一个过滤条件或者一个过滤条件的数组
```
{
  "bool": {
    "must": { "term": { "folder": "inbox" }},
    "must_not": { "term": { "tag": "spam" }},
    "should": [
      { "term": { "starred": true }},
      { "term": { "unread": true }}
    ]
  }
}
```
6. `match_all`查询  
使用`match_all`可以查询到所有文档，是没有查询条件下的默认语句
```
{
  "match_all": {}
}
```
7. `match`查询  
`match`查询是一个标准查询，不管你需要全文本查询还是精确查询基本上都要用到它  
做精确匹配搜索时，最好用过滤语句，因为过滤语句可以缓存数据  
不像在简单搜索中介绍的字符查询，`match`查询不可以用类似`+usid:2 +tweet:search`这样的语句。它只能就指定某个确切字段某个确切的值进行搜索，而你要做的就是为它指定正确的字段名以避免语法错误
```
{
  "match": {
    "tweet": "About Search"
  }
}
```
```
{ "match": { "age": 26 }}
{ "match": { "date": "2014-09-01" }}
{ "match": { "public": true }}
{ "match": { "tag": "full_text" }}
```
8. `multi_match`查询  
`multi_match`查询允许你做`match`查询的基础上同时搜索多个字段
```
{
  "multi_match": {
    "query": "full text search",
    "fields": [ "title", "body" ]
  }
}
```
9. `bool`查询  
`bool`查询与`bool`过滤相似，用于合并多个查询子句。不同的是，`bool`过滤可以直接给出是否匹配成功，而`bool`查询要计算每一个查询子句的`_score`(相关性分值) 
    + `must`查询指定文档一定要被包含
    + `must_not`查询指定文档一定不要被包含
    + `should`查询指定文档，有则可以为文档相关性加分
    + 如果`bool`查询下没有`must`子句，那至少应该有一个`should`子句。但是如果有`must`子句，那么没有`should`子句也可以进行查询
    + 以下查询将会找到`title`字段中包含"how to make millions"，并且`tag`字段没有被标为spam，如果有标识为"starred"或者发布日期为2014年之前，那么这些匹配的文档将比同类网站等级高
```
{
  "bool": {
    "must": { "match": { "title": "how to make millions" }},
    "must_not": { "match": { "tag": "spam" }},
    "should": [
      { "match": { "tag": "starred" }},
      { "range": { "date": { "gte": "2014-01-01" }}}
    ]
  }
}
```

#### 5. 带过滤的查询语句
1. search API中只能包含`query`语句，所以我们需要用`filtered`来同时包含`query`和`filter`子句，我们在外层再加入`query`的上下文关系
```
GET /_search
{
  "query": {
    "filtered": {
      "query": { "match": { "email": "business opportunity" }},
      "filter": { "term": { "folder": "inbox" }}
    }
  }
}
```
2. 在`query`上下文中，如果你只需要一条过滤语句，比如在匹配全部邮件的时候，可以省略`query`子句
```
GET /_search
{
  "query": {
    "filtered": {
      "filter": { "term": { "folder": "inbox" }}
    }
  }
}
```
3. 如果一条查询语句没有指定查询范围，那么它默认使用`match_all`查询，所以上面语句的完整形式如下
```
GET /_search
{
  "query": {
    "filtered": {
      "query": { "match_all": {}},
      "filter": { "term": { "folder": "inbox" }}
    }
  }
}
```
4. 过滤语句中可以使用`query`查询的方式代替`bool`过滤子句(我们很少用到的过滤语句中包含查询，保留这种用法只是为了语法的完整性。只有在过滤中用到全文本匹配的时候才会使用这种结构)
```
GET /_search
{
  "query": {
    "filtered": {
      "filter": {
        "bool": {
          "must": { "term": { "folder": "inbox" }},
          "must_not": {
            "query": {
              "match": { "email": "urgent business proposal" }
            }
          }
        }
      }
    }
  }
}
```
5. validate API可以验证一条查询语句是否合法
```
GET /gb/tweet/_validate/query
{
  "query": {
    "tweet" : {
      "match" : "really powerful"
    }
  }
}
```
6. 想知道语句非法的具体错误信息，需要加上`explain`参数
```
GET /gb/tweet/_validate/query?explain
{
  "query": {
    "tweet" : {
      "match" : "really powerful"
    }
  }
}
```

### 十二、排序

#### 1. 排序方式
+ 默认情况下，结果集会按照相关性进行排序(相关性分值会用`_score`字段来给出一个浮点型的数值，默认情况下，结果集以`_score`进行倒序排列)
+ 过滤语句与`_score`没有关系，但是有隐含的查询条件`match_all`为所有的文档的`_score`设值为1。也就相当于所有的文档相关性是相同的
+ 下面例子中，对结果集按照时间排序
    1. `_score`字段没有经过计算，因为它没有用作排序(想强制计算其相关性，可以设置`track_scores`为`true`)
    2. 增加了一个`sort`字段，其中`date`字段被转为毫秒当作排序依据
```
GET /_search
{
  "query" : {
    "filtered" : {
      "filter" : { "term" : { "user_id" : 1 }}
    }
  },
  "sort": { "date": { "order": "desc" }}
}
```
```
hits" : {
  "total" : 6,
  "max_score" : null, <1>
  "hits" : [ {
    "_index" : "us",
    "_type" : "tweet",
    "_id" : "14",
    "_score" : null, <1>
    "_source" : {
      "date": "2014-09-24",
      ...
    },
    "sort" : [ 1411516800000 ] <2>
  },
  ...
}
```
+ 作为缩写，你可以只指定要排序的字段名称`"sort": "number_of_children"`，字段值默认以顺序排列，而`_score`默认以倒序排列
+ 多级排序(结果集会先用第一排序字段来排序，当用用作第一字段排序的值相同的时候，然后再用第二字段对第一排序值相同的文档进行排序，以此类推)
```
GET /_search
{
  "query" : {
    "filtered" : {
      "query": { "match": { "tweet": "manage text search" }},
      "filter" : { "term" : { "user_id" : 2 }}
    }
  },
  "sort": [
    { "date": { "order": "desc" }},
    { "_score": { "order": "desc" }}
  ]
}
```
+ 字符查询也支持自定义排序，在查询字符串使用`sort`参数就可以`GET /_search?sort=date:desc&sort=_score&q=text`
+ 对于数字和日期，可以从多个值中取出一个来进行排序，你可以使用`min`,`max`,`avg`或`sum`这些模式。比说你可以在`dates`字段中用最早的日期来进行排序
```
"sort": {
  "dates": {
    "order": "asc",
    "mode": "min"
  }
}
```
+ `analyzed`字符串字段同时也是多值字段，在这些字段上排序往往得不到你想要的值，为了使一个string字段可以进行排序，它必须是完整的`not_analyzed`字符串(对`analyzed`字段进行强制排序会消耗大量内存)
    1. `tweet`字段用于全文本的`analyzed`索引方式不变
    2. 新增的`tweet.raw`子字段索引方式是`not_analyzed`
```
"tweet": { <1>
  "type": "string",
  "analyzer": "english",
  "fields": {
    "raw": { <2>
      "type": "string",
      "index": "not_analyzed"
    }
  }
}
```
+ 给数据重建索引后，我们既可以使用`tweet`字段进行全文本搜索，也可以用`tweet.raw`字段进行排序
```
GET /_search
{
  "query": {
    "match": {
      "tweet": "elasticsearch"
    }
  },
  "sort": "tweet.raw"
}
```

#### 2. 相关性简介
+ 每个文档都有相关性评分，用一个相对的浮点数字段`_score`来表示(`_score`的评分越高，相关性越高)
+ 查询语句会为每个文档添加一个`_score`字段，评分的计算方式取决于不同的查询类型，不同的查询语句用于不同的目的
+ `fuzzy`查询会计算与关键词的拼写相似程度，`terms`查询会计算找到的内容与关键词组成部分匹配的百分比，但是一般意义上我们说的全文本搜索是指计算内容与关键词的类似程度
+ ElasticSearch的相似度算法被定义为TF/IDF，即检索词频率/反向文档频率
    1. 检索词频率：出现频率越高，相关性也越高。字段中出现过5次要比只出现过1次的相关性高
    2. 反向文档频率：检索词出现在多数文档中会比出现在少数文档中的权重更低，即检验一个检索词在文档中的普遍重要性
    3. 字段长度准则：检索词出现在一个短的title要比同样的词出现在一个长的content字段权重更高
+ 单个查询可以使用TF/IDF评分标准或其他方式，比如短语查询中检索词的距离或模糊查询里的检索词相似度
+ 相关性并不只是全文本检索的专利。也适用于`yes|no`的子句，匹配的子句越多，相关性评分越高
+ 如果多条查询子句被合并为一条复合查询语句，比如`bool`查询，则每个查询子句计算得出的评分会被合并到总的相关性评分中

#### 3. 理解评分标准
+ 当调试一条复杂的查询语句时，想要理解相关性评分`_score`是比较困难的。ElasticSearch在每个查询语句中都有一个`explain`参数，将`explain`设为`true`就可以得到更详细的信息
    1. `explain`参数可以让返回结果添加一个`_score`评分的得来依据
```
GET /_search?explain <1>
{
  "query" : { "match" : { "tweet" : "honeymoon" }}
}
```
+ 增加一个`explain`参数会为每个匹配到的文档产生一大堆额外内容，但是花时间去理解它是很有意义的
    1. 普通查询返回的元数据
    2. 加入了该文档来自于哪个节点哪个分片上的信息(因为词频率和文档频率是在每个分片中计算出来的)
    3. `honeymoon`相关性评分计算的总结。告诉了我们`honeymoon`在`tweet`字段中的检索词频率/反向文档频率或TF/IDF
    4. 检索词频率。检索词`honeymoon`在`tweet`字段中的出现次数
    5. 反向文档频率。检索词`honeymoon`在`tweet`字段在当前文档出现次数与索引中其他文档的出现总数的比率
    6. 字段长度准则。
```
{
  "_index" : "us",
  "_type" : "tweet",
  "_id" : "12",
  "_score" : 0.076713204,
  "_source" : { ... trimmed ... }, <1>
  "_shard" : 1,
  "_node" : "mzIVYCsqSWCG_M_ZffSs9Q", <2>
  "_explanation": { <3>
    "description": "weight(tweet:honeymoon in 0)[PerFieldSimilarity], result of:",
    "value": 0.076713204,
    "details": [
      {
        "description": "fieldWeight in 0, product of:",
        "value": 0.076713204,
        "details": [
          { <4>
            "description": "tf(freq=1.0), with freq of:",
            "value": 1,
            "details": [
              {
                "description": "termFreq=1.0",
                "value": 1
              }
            ]
          },
          { <5>
            "description": "idf(docFreq=1, maxDocs=1)",
            "value": 0.30685282
          },
          { <6>
            "description": "fieldNorm(doc=0)",
            "value": 0.25,
          }
        ]
      }
    ]
  }
}
```
+ JSON形式的`explain`描述是难以阅读的但是转成YAML会好很多，只需要在参数中加上`format=yaml`
+ 当`explain`选项加到某一文档上时，它会告诉你为何这个文档会被匹配，以及一个文档为何没有被匹配
```
GET /us/tweet/12/_explain
{
  "query" : {
    "filtered" : {
      "filter" : { "term" : { "user_id" : 2 }},
      "query" : { "match" : { "tweet" : "honeymoon" }}
    }
  }
}
```
+ 除了上面我们看到的完整描述外，我们还可以看到这样的描述：`failure to match filter: cache(user_id:[2 TO 2])`(也就是说我们的`user_id`过滤子句使该文档不能匹配到)

#### 4. 数据字段
+ 当对一个字段进行排序时，ElasticSearch需要进入每个匹配到的文档得到相关的值。倒排索引在用于搜索时是非常卓越的，但却不是理想的排序结构
    + 当搜索的时候，我们需要用检索词去遍历所有的文档
    + 当排序的时候，我们需要遍历文档中所有的值，我们需要做反倒序排列操作
+ 为了提高排序效率，ElasticSearch会将所有字段的值加载到内存中，这就叫做数据字段(ElasticSearch将所有字段数据加载到内存中并不是匹配到的那部分数据。而是索引下所有文档中的值，包括所有类型)
+ 将所有字段数据加载到内存中是因为从硬盘反向倒排索引是非常缓慢的。尽管你这次请求需要的是某些文档中的部分数据，但你下个请求却需要另外的数据，所以将所有字段数据一次性加载到内存中是十分必要的。
+ ElasticSearch中的字段数据常被应用到以下场景：
    1. 对一个字段进行排序
    2. 对一个字段进行聚合
    3. 某些过滤，比如地理位置过滤
    4. 某些与字段相关的脚本计算
+ 毫无疑问，这会消耗掉很多内存，尤其是大量的字符串数据(string字段可能包含很多不同的值，比如邮件内容)
+ 值得庆幸的是，内存不足是可以通过横向扩展解决的，我们可以增加更多的节点到集群

### 十三、分布式搜索的执行方式
+ 一个CRUD操作(create read update delete)只处理一个单独的文档。文档的唯一性由`_index`,`_type`和`routing-value`(通常默认是该文档的`_id`)的组合来确定。这意味着我们可以准确知道集群中的哪个分片持有这个文档
+ 由于不知道哪个文档会匹配查询(文档可能存放在集群中的任意分片上)，所以搜索需要一个更复杂的模型。一个搜索不得不通过查询每一个我们感兴趣的索引的分片副本，来看是否含有任何匹配的文档
+ 但是，找到所有匹配的文档只完成了这件事的一半。在搜索(search)API返回一页结果前，来自多个分片的结果必须被组合放到一个有序列表中。因此，搜索的执行过程分两个阶段，称为查询然后取回(query then fetch)