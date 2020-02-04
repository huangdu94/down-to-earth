## Elasticsearch笔记

### 一、Elasticsearch概述

#### 1. Elasticsearch介绍
+ Elasticsearch是一个实时分布式搜索和分析引擎，它让你以前所未有的速度处理大数据成为可能
+ Elasticsearch是一个基于Apache Lucene(TM)的开源搜索引擎
+ 无论在开源还是专有领域，Lucene可以被认为是迄今为止最先进、性能最好的、功能最全的搜索引擎库

#### 2. 安装并运行Elasticsearch
+ 安装Elasticsearch唯一的要求是安装[java](www.java.com)
+ 你可以下载最新版本的[elasticsearch](elasticsearch.org/download)
+ 后台运行 `./bin/elasticsearch -d`
+ 查看es是否正常运行 `curl 'http://localhost:9200/?pretty'`
+ 关闭es `curl -XPOST 'http://localhost:9200/_shutdown'`

#### 3. 和Elasticsearch交互
+ 基于HTTP协议，以JSON为数据交互格式的RESTful API
+ `curl -X<VERB> <PROTOCOL>://<HOST>/<PORT>?<QUERY_STRING> -d <BODY>`
    + `VERB` http方法:`GET`,`POST`,`PUT`,`HEAD`,`DELETE`
    + `PROTOCOL` `http`或`https`协议(只有在Elasticsearch前面有https代理的时候可用)
    + `HOST` Elasticsearch集群中的任何一个节点的主机名，如果是在本地的节点，那么就叫`localhost`
    + `PORT` Elasticsearch HTTP服务所在的端口，默认为`9200`
    + `QUERY_STRING` 一些可选的查询请求参数，例如`?pretty`参数将使请求返回更加美观易读的JSON数据
    + `BODY` 一个JSON格式的请求主体(如果请求需要的话)
+ linux利用`curl`命令注意`-d`后面的参数部分有些特殊字符需要转义(例`,`)
+ linux利用`curl`命令，请求体可放在文件中 `curl -XPOST http://localhost:9200/_bulk --data-binary @requests`(bulk API需要，其中`requests`为请求体文件)
+ 简写表示形式，省略`PROTOCOL`,`HOST`,`PORT`(计算集群中的文档数量) 
```
GET /_count
{
  "query": {
    "match_all": {}
  }
}
```

#### 4. 面向文档
+ Elastic本质上是一个分布式数据库，允许多台服务器协同工作，每台服务器可以运行多个Elastic实例
+ 单个Elastic实例称为一个节点(node)，一组节点构成一个集群(cluster)
+ Elastic数据管理的顶层单位就叫做Index(索引)，相当于单个数据库，每个Index(即数据库)的名字必须是小写(Elastic会索引所有字段，经过处理后写入一个反向索引(Inverted Index)，查找数据的时候，直接查找该索引)
+ Index里面单条的记录称为Document(文档)，许多条Document构成了一个Index，Document使用JSON格式表示
+ Document可以分组，这种分组就叫做Type，它是虚拟的逻辑分组，用来过滤Document，不同的Type应该有相似的结构(schema)
+ id字段不能在这个组是字符串，在另一个组是数值，这是与关系型数据库的表的一个区别，性质完全不同的数据(比如products和logs)应该存成两个Index，而不是一个Index里面的两个Type(虽然可以做到)
+ Elastic与关系数据库的对比  
|关系数据库|数据库|表|行|列|
|:-:|:-:|:-:|:-:|:-:|
|Elasticsearch|索引(Index)|类型(Type)|文档(Documents)|字段(Fields)|

#### 5. Elasticsearch简单教程
1. 索引员工文档
```
PUT /iflytek/employee/1
{
  "first_name" : "John",
  "last_name" : "Smith",
  "age" : 25,
  "about" : "I love to go rock climbing",
  "interests": [ "sports", "music" ]
}
PUT /iflytek/employee/2
{
  "first_name" : "Jane",
  "last_name" : "Smith",
  "age" : 32,
  "about" : "I like to collect rock albums",
  "interests": [ "music" ]
}
PUT /iflytek/employee/3
{
  "first_name" : "Douglas",
  "last_name" : "Fir",
  "age" : 35,
  "about": "I like to build cabinets",
  "interests": [ "forestry" ]
}
```
2. 根据id操作员工文档
    + `GET /iflytek/employee/1` 查
    + `DELETE /iflytek/employee/1` 删
    + `PUT /iflytek/employee/1` 改
    + `HEAD /iflytek/employee/1` 检查某文档是否存在(`curl`使用时加`-i`参数，信息在响应头中)
3. 轻量搜索
    + `GET /iflytek/employee/_search` 默认情况下搜索会返回前10个结果
    + `GET /iflytek/employee/_search?q=last_name:Smith` 查询字符串(query string)搜索，轻量级搜索
    + `GET /iflytek/employee/_search?q=age[30 TO 60]&sort=age:desc&from=0&size=2` 查询年龄为30-60之间，降序排序，分页查询
4. 使用查询表达式搜索(DSL,Domain Specific Language,特定领域语言)
    + 搜索指定字段值
    ```
    GET /iflytek/employee/_search
    {
      "query" : {
        "match" : {
          "last_name" : "Smith"
        }
      }
    }
    ```
    + 搜索指定字段值(加过滤器)
    ```
    GET /iflytek/employee/_search
    {
      "query" : {
        "filtered" : {
          "filter" : {
            "range" : {
              "age" : { "gt" : 30 }
            }
          },
          "query" : {
            "match" : {
              "last_name" : "smith"
            }
          }
        }
      }
    }
    ```
    + 全文搜索
    ```
    GET /iflytek/employee/_search
    {
      "query" : {
        "match" : {
          "about" : "rock climbing"
        }
      }
    }
    ```
    + 短语搜索(比全文搜索更具有针对性)
    ```
    GET /iflytek/employee/_search
    {
      "query" : {
        "match_phrase" : {
          "about" : "rock climbing"
        }
      }
    }
    ```
    + 高亮搜索
    ```
    GET /iflytek/employee/_search
    {
      "query" : {
        "match_phrase" : {
          "about" : "rock climbing"
        }
      },
      "highlight": {
        "fields" : {
          "about" : {}
        }
      }
    }
    ```
    + 分析(聚合查询)
    ```
    GET /iflytek/employee/_search
    {
      "aggs": {
        "all_interests": {
          "terms": { "field": "interests" }
        }
      }
    }
    ```
    + 聚合和精确查询组合
    ```
    GET /iflytek/employee/_search
    {
      "query": {
        "match": {
          "last_name": "smith"
        }
      },
      "aggs": {
        "all_interests": {
          "terms": {
            "field": "interests"
          }
        }
      }
    }
    ```
    + 分级汇总(统计每种兴趣下职员的平均年龄)
    ```
    GET /iflytek/employee/_search
    {
      "aggs" : {
        "all_interests" : {
          "terms" : { 
            "field" : "interests" 
          },
          "aggs" : {
            "avg_age" : {
              "avg" : { 
                "field" : "age" 
              }
            }
          }
        }
      }
    }
    ```
    
#### 6. 分布式特性
+ Elasticsearch可以横向扩展至数百(甚至数千)的服务器节点，同时可以处理PB级数据。Elasticsearch天生就是分布式的，并且在设计时屏蔽了分布式的复杂性
+ Elasticsearch在分布式方面几乎是透明的

#### 7. Java API连接ES
+ Java连接ES(即获得ES的Client)，Elasticsearch创建Client有几种方式。首先在Elasticsearch的配置文件elasticsearch.yml中定义cluster.name
1. 创建方式一：节点方式创建
```java
Node node = NodeBuilder().clusterName("yourclustername").node();
Client client = node.client();
```
2. 创建方式二：指定ip地址创建
```java
Client client = TransportClient.builder().build()
.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300));
```
3. 创建方式三：按集群名称创建
```java
Settings settings = Settings.settingsBuilder()
.put("cluster.name", "sojson-application").build();
Client client = TransportClient.builder().settings(settings).build();
```
4. 创建方式四：同一内网Ip段，嗅的方式自己查找，组成集群
```java
Settings settings = Settings.settingsBuilder()
.put("client.transport.sniff", true).build();
TransportClient client = TransportClient.builder().settings(settings).build();
```
    
### 二、Elasticsearch分布式

#### 1. 集群内的原理
+ 集群健康 `GET /_cluster/health`
+ 创建索引(设置主分片数`number_of_shards`和复制分片数`number_of_replicas`)
```
PUT /website
{
  "settings" : {
    "number_of_shards" : 3,
    "number_of_replicas" : 1
  }
}
```
+ 手动分配分片所在节点的方式
    1. 关闭分片自动分配所在节点(改为`all`开启)
    ```
    PUT 10.3.172.112:9200/_cluster/settings
    {
      "transient":{
        "cluster.routing.allocation.enable":"none"
      }
    }
    ```
    2. 移动指定分片到指定节点  
    ```
    POST /_cluster/reroute
    {
      "commands": [
        {
          "move": {
            "index": "website",
            "shard": 1,
            "from_node": "10.3.172.112",
            "to_node": "10.3.172.111"
          }
        }
      ]
    }
    ```

#### 2. 分布式文档存储
1. `routing`参数
    + `shard = hash(routing) % number_of_primary_shards` 计算文档属于哪个分片
    + `get`,`index`,`delete`,`bulk`,`update`,`mget`都可接收一个`routing`参数，它用来自定义文档到分片的映射
2. `replication`参数
    + `replication`复制默认的值是`sync`这将导致主分片得到复制分片的成功响应后才返回
    + 如果你设置`replication`为`async`，请求在主分片上被执行后就会返回给客户端，它依旧会转发请求给复制节点，但你将不知道复制节点成功与否
    + 上面的这个选项不建议使用，默认的`sync`复制允许Elasticsearch强制反馈传输，`async`复制可能会因为在不等待其它分片就绪的情况下发送过多的请求而使Elasticsearch过载
3. `consistency`参数
    + 默认主分片在尝试写入时需要规定数量(quorum)或过半的分片(可以是主节点或复制节点)可用，这是防止数据被写入到错的网络分区
    + 规定的数量计算公式`int( (primary + number_of_replicas) / 2 ) + 1`
4. `timeout`参数
    + 当分片副本不足时Elasticsearch会等待更多的分片出现，默认等待一分钟
    + 可以设置`timeout`参数让它终止的更早，`100`表示100毫秒，`30s`表示30秒
+ `update`API接受`routing`,`replication`,`consistency`和`timout`参数
+ `mget`API的`routing`参数可以被docs中的每个文档设置
+ `bulk`API还可以在最上层使用`replication`和`consistency`参数，`routing`参数则在每个请求的元数据中使用

    
### 三、数据输入和输出

#### 1. 文档元数据
+ 一个文档不只有数据，它还包含了元数据(metadata):
+ `_index` 文档存储的地方
+ `_type` 文档代表的对象的类(每个类型type都有自己的映射mapping或者结构定义， 就像传统数据库表中的列一样)
+ `_id` 文档的唯一标识
+ 其它元数据(例版本号`_version`)

#### 2. 索引文档
+ 指定id新增  
```
PUT /website/blog/123
{
  "title": "My first blog entry",
  "text": "Still trying this out...",
  "date": "2014/01/01"
}
```
+ 自增id新增
```
POST /website/blog/
{
  "title": "My second blog entry",
  "text": "Still trying this out...",
  "date": "2014/01/02"
}
```

#### 3. 检索文档
+ `GET /website/blog/123?pretty` 美化输出
+ `GET /website/blog/123?_source=title,text` 检索文档的指定字段
+ `GET /website/blog/123/_source` 不要元数据
+ `curl -XHEAD -i http://localhost:9200/website/blog/123` (检查文档是否存在)

#### 4. 更新整个文档
+ 文档在Elasticsearch中是不可变的，我们不能修改他们。如果需要更新已存在的文档，可以使用索引文档章节提到的index API重建索引(reindex)
+ update API似乎允许你修改文档的局部，但事实上Elasticsearch遵循与之前所说完全相同的过程  
```
PUT /website/blog/123
{
  "title": "My first blog entry",
  "text": "I am starting to get the hang of this...",
  "date": "2014/01/01"
}
```

#### 5. 创建一个新文档
+ 使用自增id保证文档新增而不是覆盖原来的
```
POST /website/blog/
{ ... }
```
+ 使用指定id，在id已被使用的时候创建失败，方法一
```
PUT /website/blog/123?op_type=create
{ ... }
```
+ 使用指定id，在id已被使用的时候创建失败，方法二
```
PUT /website/blog/123/_create
{ ... }
```

#### 6. 删除文档
+ 删除一个文档也不会立即从磁盘上移除，它只是被标记成已删除。Elasticsearch将会在你之后添加更多索引的时候才会在后台进行删除内容的清理
+ `DELETE /website/blog/123`

#### 7. Elasticsearch版本控制
+ 为了保证数据在多线程操作下的准确性ES要进行版本控制(CAS无锁)
+ 悲观锁和乐观锁
    + 悲观锁：假设会发生并发冲突，屏蔽一切可能违反数据准确性的操作
    + 乐观锁：假设不会发生并发冲突，只在提交操作时检查是否违反数据完整性
+ 内部版本控制和外部版本控制
    + 内部版本控制：`_version`自增长，修改数据后，`_version`会自动的加1
    + 外部版本控制：为了保持`_version`与外部版本控制的数值一致
    + 使用`version_type=external`检查数据当前的version值是否小于请求中的version值
    
#### 8. 带版本控制的操作
+ 我们只希望文档的`_version`是1时更新才生效
```
PUT /website/blog/1?version=1
{
  "title": "My first blog entry",
  "text": "Starting to get the hang of this..."
}
```
+ 使用外部版本号
```
PUT /website/blog/2?version=5&version_type=external
{
  "title": "My first external blog entry",
  "text": "Starting to get the hang of this..."
}
```
    
#### 9. 文档局部更新(本质上同更新整个文档)
+ update API
```
POST /website/blog/1/_update
{
  "doc" : {
    "tags" : [ "testing" ],
    "views": 0
  }
}
```
+ 使用脚本局部更新(需要在elasticsearch.yml文件中配置`script.inline`和`script.indexed`为`true`)
+ 使用脚本指定字段增加一
```
POST /website/blog/1/_update
{
  "script" : "ctx._source.views+=1"
}
```
+ 使用脚本指定字段增加内容，内容从参数指定
```
POST /website/blog/1/_update
{
  "script" : "ctx._source.tags+=new_tag",
  "params" : {
    "new_tag" : "search"
  }
}
```
+ 当满足特定条件的时候，脚本执行特定操作
```
POST /website/blog/1/_update
{
  "script" : "ctx.op = ctx._source.views == count ? 'delete' : 'none'",
  "params" : {
    "count": 1
  }
}
```
+ 当脚本所指定字段不存在时，初始化
```
POST /website/pageviews/1/_update
{
  "script" : "ctx._source.views+=1",
  "upsert": {
    "views": 1
  }
}
```
+ 当更新发生冲突时，重新执行(5次)
```
POST /website/pageviews/1/_update?retry_on_conflict=5
{
  "script" : "ctx._source.views+=1",
  "upsert": {
    "views": 0
  }
}
```
    
#### 10. 检索多个文档(mget API)
+ mget API参数是一个docs数组，数组的每个节点定义一个文档的`_index`、`_type`、`_id`元数据，如果你只想检索一个或几个确定的字段，也可以定义一个`_source`参数
```
GET /_mget
{
  "docs":[
    {
      "_index" : "website",
      "_type" : "blog",
      "_id" : 2
    },
    {
      "_index" : "website",
      "_type" : "pageviews",
      "_id" : 1,
      "_source": "views"
    }
  ]
}
```
+ 公共的参数可以在请求地址中指定，但是请求体中依然可以覆盖请求地址中参数
```
GET /website/blog/_mget
{
  "docs" : [
    { "_id" : 2 },
    { "_type" : "pageviews", "_id" : 1 }
  ]
}
```
+ 查询多个id的简写方式
```
GET /website/blog/_mget
{
  "ids" : [ "2", "1" ]
}
```
    
#### 11. 更省时的批量操作
+ bulk API允许我们使用单一请求来实现多个文档的`create` `index` `update`或`delete`。这对索引类似于日志活动这样的数据流非常有用，它们可以以成百上千的数据为一个批次按序进行索引
```
{ action: { metadata }}\n
{ request body }\n
{ action: { metadata }}\n
{ request body }\n
...
```
+ 每行必须以`\n`符号结尾，包括最后一行。这些都是作为每行有效的分离而做的标记
+ 每一行的数据不能包含未被转义的换行符，它们会干扰分析——这意味着JSON不能被美化打印
+ 删除
```
{ "delete": { "_index": "website", "_type": "blog", "_id": "123" }}
```
+ 创建，指定id
```
{ "create": { "_index": "website", "_type": "blog", "_id": "123" }}
{ "title": "My first blog post" }
```
+ 创建，不指定id
```
{ "index": { "_index": "website", "_type": "blog" }}
{ "title": "My second blog post" }
```
+ 放在一起例子(记得最后一个换行符)
```
POST /_bulk
{ "delete": { "_index": "website", "_type": "blog", "_id": "123" }}
{ "create": { "_index": "website", "_type": "blog", "_id": "123" }}
{ "title": "My first blog post" }
{ "index": { "_index": "website", "_type": "blog" }}
{ "title": "My second blog post" }
{ "update": { "_index": "website", "_type": "blog", "_id": "123", "_retry_on_conflict" : 3} }
{ "doc" : {"title" : "My updated blog post"} }
```
+ 重复的内容可以不写在请求体中(依然可以覆盖，同mget)
```
POST /website/_bulk
{ "index": { "_type": "log" }}
{ "event": "User logged in" }
```
```
POST /website/log/_bulk
{ "index": {}}
{ "event": "User logged in" }
{ "index": { "_type": "blog" }}
{ "title": "Overriding the default type" }
```
+ 最佳大小并不是一个固定的数字。它完全取决于你的硬件、你文档的大小和复杂度以及索引和搜索的负载
+ 一个好的批次最好保持在5-15MB大小间

### 四、搜索(基本的工具)

#### 1. 空搜索
+ `GET /_search`
+ `GET /_search?timeout=10ms`
+ 需要注意的是`timeout`不会停止执行查询，它仅仅告诉你目前顺利返回结果的节点然后关闭连接。在后台，其他分片可能依旧执行查询，尽管结果已经被发送

#### 2. 多索引和多类型
+ `/_search` 在所有索引的所有类型中搜索
+ `/gb/_search` 在索引gb的所有类型中搜索
+ `/gb,us/_search` 在索引gb和us的所有类型中搜索
+ `/g*,u*/_search` 在以g或u开头的索引的所有类型中搜索
+ `/gb/user/_search` 在索引gb的类型user中搜索
+ `/gb,us/user,tweet/_search` 在索引gb和us的类型为user和tweet中搜索
+ `/_all/user,tweet/_search` 在所有索引的user和tweet中搜索

#### 3. 分页搜索
+ `GET /_search?size=5`(`size`默认10)
+ `GET /_search?size=5&from=5`(`from`跳过开始的结果数，默认0)
+ 注意深度分页影响性能
    
#### 4. 轻量搜索
+ search API有两种
    1. 简易版的查询字符串(query string)将所有参数通过查询字符串定义
    2. 使用JSON完整的表示请求体(request body)，这种富搜索语言叫做结构化查询语句(DSL)
+ 查询字符串搜索对于在命令行下运行点对点(ad hoc)查询特别有用
    + `GET /_all/tweet/_search?q=tweet:elasticsearch`
+ `+`前缀表示语句匹配条件必须被满足，`-`前缀表示条件必须不被满足，所有条件如果没有`+`或`-`表示条件是可选的
    + 条件 `+name:john +tweet:mary` url编码(percent encoding，百分比编码)
    + `GET /_search?q=%2Bname%3Ajohn+%2Btweet%3Amary` 
+ 返回包含"mary"字符的所有文档的简单搜索
    + `GET /_search?q=mary` 
+ 更复杂的语句
    + 条件：1.`name`字段包含`mary`或`john` 2.`date`晚于`2014-09-10` 3.`_all`字段包含`aggregations`或`geo`
    + `+name:(mary john) +date:>2014-09-10 +(aggregations geo)` 
    + `GET /_search?q=%2Bname%3A(mary+john)+%2Bdate%3A%3E2014-09-10+%2B(aggregations+geo)`

### 五、映射和分析

#### 1. 确切值(Exact values)和全文(Full text)
+ Elasticsearch中的数据可以概括的分为两类，精确值和全文
+ 精确值如它们听起来那样精确。例如日期或者用户ID，但字符串也可以表示精确值，例如用户名或邮箱地址。对于精确值来讲，`Foo`和`foo`是不同的，`2014`和`2014-09-15`也是不同的
+ 全文是指文本数据(通常以人类容易识别的语言书写)，例如一个推文的内容或一封邮件的内容
+ 精确值很容易查询，结果是二进制的，要么匹配查询，要么不匹配。查询全文数据要微妙的多，我们问的不只是这个文档匹配查询吗，而是该文档匹配查询的程度有多大

#### 2. 倒排索引
1. 为了方便在全文文本字段中进行这些类型的查询，Elasticsearch首先对文本分析(analyzes)，然后使用结果建立一个倒排索引
2. Elasticsearch使用一种叫做倒排索引(inverted index)的结构来做快速的全文搜索。倒排索引由在文档中出现的唯一的单词列表，以及对于每个单词在文档中的位置组成
3. 为了创建倒排索引，我们首先切分每个文档的指定字段为单独的单词(`terms` `tokens`)把所有的唯一词放入列表并排序
4. 为了找到确实存在于索引中的词，索引文本和查询字符串都要标准化为相同的形式，这个表征化和标准化的过程叫做分词(analysis)

#### 3. 分析与分析器
+ 一个分析器(analyzer)只是一个包装用于将三个功能放到一个包里
    1. 字符串经过字符过滤器(character filter)，它们的工作是在表征化前处理字符串。字符过滤器能够去除HTML标记，或者转换 "&" 为 "and" 
    2. 分词器(tokenizer)将字符串表征化为独立的词(断词)。一个简单的分词器(tokenizer)可以根据空格或逗号将单词分开
    3. 每个词都通过所有表征过滤(token filters)，它可以修改词(例如将"Quick"转为小写)，去掉词(例如停用词像"a"、"and"、"the"等等)，或者增加词(例如同义词像"jump"和"leap")
+ Elasticsearch提供很多开箱即用的字符过滤器，分词器和表征过滤器。这些可以组合来创建自定义的分析器以应对不同的需求
+ Elasticsearch还附带了一些预装的分析器，可以直接使用它们(以下列出各分词器处理"Set the shape to semi-transparent by calling set_trans(5)"的结果)
    1. 标准分析器(`standard`，Elasticsearch默认) (set, the, shape, to, semi, transparent, by, calling, set_trans, 5)
    2. 简单分析器(`simple`) (set, the, shape, to, semi, transparent, by, calling, set, trans)
    3. 空格分析器(`whitespace`) (Set, the, shape, to, semi-transparent, by, calling, set_trans(5))
    4. 语言分析器(`english`) (set, shape, semi, transpar, call, set_tran, 5)
+ 测试分析器
    + 查看指定文档指定字段分词情况  
    `GET /iflytek/employee/1/_termvectors?fields=about`
    + 指定分词器分词  
    `POST /_analyze?analyzer=standard {"text": "text content"}`
    + 使用指定字段分词器分词  
    `POST /iflytek/_analyze?field=about {"text": "text content"}`
+ 指定分析器 当Elasticsearch在你的文档中探测到一个新的字符串字段，它将自动设置它为全文string字段并用standard分析器分析(可以通过映射`mapping`设置)

#### 4. 映射
1. Elasticsearch为对字段类型进行猜测，动态生成了字段和类型的映射关系。默认字段`_all`是`string`类型
2. 核心简单字段类型
|类型|表示的数据类型|
|:-:|:-:|
|String|string|
|Whole number|byte,short,integer,long|
|Floating point|float,double|
|Boolean|boolean|
|Date|date|
3. 当你索引一个包含新字段的文档，一个之前没有的字段，Elasticsearch将使用动态映射猜测字段类型，这类型来自于JSON的基本数据类型，使用以下规则
|JSON type|Field type|
|:-:|:-:|
|Boolean: true or false|boolean|
|Whole number: 123|long|
|Floating point: 123.45|double|
|String, valid date: "2014-09-15"|date|
|String: "foo bar"|string|
4. 查看映射
    + `GET /gb/_mapping/tweet`
    + `GET /gb/_mapping`
5. 自定义字段映射
    + 其它类型(`index`可设置`not_analyzed` `no`)
    ```
    {
      "number_of_clicks": {
        "type": "integer"
      }
    }
    ```
    + `string`类型(`index`参数控制字符串以何种方式被索引，可设置`analyzed` `not_analyzed` `no`)
    ```
    {
      "tag": {
        "type": "string",
        "index": "not_analyzed"
      }
    }
    ```
    + 指定分词器
    ```
    {
      "tweet": {
        "type": "string",
        "analyzer": "english"
      }
    }
    ```
    + 更新映射
        + 可以向已有映射中增加字段，但不能修改它。如果一个字段在映射中已经存在，这可能意味着那个字段的数据已经被索引。如果改变了字段映射，那已经被索引的数据将错误并且不能被正确的搜索到
        + 可以更新一个映射来增加一个新字段，但是不能把已有字段的类型那个从analyzed改到not_analyzed
        + 可以通使用analyze API测试字符串字段的映射(使用指定字段的分词设置)   

#### 5. 复杂核心域类型
1. 多值域
    + 数组中所有值必须为同一类型
    + 创建一个新字段，这个字段索引了一个数组，Elasticsearch将使用第一个值的类型来确定这个新字段的类型
2. 空域(这四个字段将被识别为空字段而不被索引)
```
"empty_string": "",
"null_value": null,
"empty_array": [],
"array_with_null_value": [null]
```
3. 多层级对象
```
{
  "tweet": "Elasticsearch is very flexible",
  "user": {
    "id": "@johnsmith",
    "gender": "male",
    "age": 26,
    "name": {
      "full": "John Smith",
      "first": "John",
      "last": "Smith"
    }
  }
}
```
4. 内部对象的映射(Elasticsearch会动态的检测新对象的字段，并且映射它们为`object`类型，将每个字段加到`properties`字段下)
```
{
  "gb": {
    "tweet": {
      "properties": {
        "tweet": { "type": "string" },
        "user": {
          "type": "object",
          "properties": {
            "id": { "type": "string" },                      
            "gender": { "type": "string" },
            "age": { "type": "long" },
            "name": {
              "type": "object",
              "properties": {
                "full": { "type": "string" },
                "first": { "type": "string" },
                "last": { "type": "string" }
              }
            }
          }
        }
      }
    }
  }
}
```
5. 内部对象是如何索引的
```
{
  "tweet": [elasticsearch, flexible, very],
  "user.id": [@johnsmith],
  "user.gender": [male],
  "user.age": [26],
  "user.name.full": [john, smith],
  "user.name.first": [john],
  "user.name.last": [smith]
}
```
6. 内部对象数组是如何索引的(扁平化处理)
```
{"followers":[{"age":35,"name":"Mary White"},{"age":26,"name":"Alex Jones"},{"age":19,"name":"Lisa Smith"}]
"followers.age": [19, 26, 35], "followers.name": [alex, jones, lisa, smith, mary, white]
```

### 六、请求体查询

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

#### 2. 查询表达式
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
+ 查询子句一般结构
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
+ 查询子句示例
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
+ 查询语句会询问每个文档的字段值与特定值的匹配程度如何
+ 原则上来说，使用查询语句做全文本搜索或其他需要进行相关性评分的时候，剩下的全部用过滤语句
+ 查询语句和过滤语句可以放在各自的上下文中，在ElasticSearch API中我们会看到许多带有`query`或`filter`的语句
+ 这些语句既可以包含单条`query`语句，也可以包含一条`filter`子句。这些语句需要首先创建一个`query`或`filter`的上下文关系
+ 复合查询语句可以加入其他查询子句，复合过滤语句也可以加入其他过滤子句。通常情况下，一条查询语句需要过滤语句的辅助，全文本搜索除外
+ 查询语句可以包含过滤子句，反之亦然。以便于我们切换`query`或`filter`的上下文。这就要求我们在读懂需求的同时构造正确有效的语句

#### 4. 最重要的查询
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

#### 6. 验证查询
1. validate API可以验证一条查询语句是否合法
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
2. 想知道语句非法的具体错误信息，需要加上`explain`参数
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

### 七、排序与相关性

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
+ 排序结果
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
+ 字符查询也支持相关性排序，在查询字符串使用`sort`参数就可以`GET /_search?sort=date:desc&sort=_score&q=text`
+ 对于数字和日期数组，可以从多个值中取出一个来进行排序，你可以使用`min`,`max`,`avg`或`sum`这些模式。比说你可以在`dates`字段中用最早的日期来进行排序
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

### 八、执行分布式检索

#### 1. 查询和取回阶段
+ 一个CRUD操作(create read update delete)只处理一个单独的文档。文档的唯一性由`_index`,`_type`和`routing-value`(通常默认是该文档的`_id`)的组合来确定。这意味着我们可以准确知道集群中的哪个分片持有这个文档
+ 由于不知道哪个文档会匹配查询(文档可能存放在集群中的任意分片上)，所以搜索需要一个更复杂的模型。一个搜索不得不通过查询每一个我们感兴趣的索引的分片副本，来看是否含有任何匹配的文档
+ 但是，找到所有匹配的文档只完成了这件事的一半。在搜索(search)API返回一页结果前，来自多个分片的结果必须被组合放到一个有序列表中。因此，搜索的执行过程分两个阶段，称为查询然后取回(query then fetch)

#### 2. 搜索选项
1. `preference`(偏爱)  
`preference`参数允许你控制使用哪个分片或节点来处理搜索请求(接受如下一些参数`_primary` `_primary_first` `_local` `_only_node:xyz` `_prefer_node:xyz` `_shards:2,3`)。然而通常最有用的值是一些随机字符串，它们可以避免结果震荡问题(the bouncing results problem)
2. `timeout`(超时)  
通常，协调节点会等待接收所有分片的回答。如果有一个节点遇到问题，它会拖慢整个搜索请求。`timeout`参数告诉协调节点最多等待多久，就可以放弃等待而将已有结果返回 
3. `routing`(路由选择)  
在路由值那节里，我们解释了如何在建立索引时提供一个自定义的`routing`参数来保证所有相关的document(如属于单个用户的document)被存放在一个单独的分片中。在搜索时，你可以指定一个或多个`routing`值来限制只搜索那些分片而不是搜索`index`里的全部分片。`GET /_search?routing=user_1,user2`
4. search_type(搜索类型)  
虽然`query_then_fetch`是默认的搜索类型，但也可以根据特定目的指定其它的搜索类型，例如：`GET /_search?search_type=count`
    + `count`  
    `count`(计数)搜索类型只有一个query(查询)的阶段。当不需要搜索结果只需要知道满足查询的document的数量时，可以使用这个查询类型
    + `query_and_fetch`  
    `query_and_fetch`(查询并且取回)搜索类型将查询和取回阶段合并成一个步骤。这是一个内部优化选项，当搜索请求的目标只是一个分片时可以使用，例如指定了`routing`(路由选择)值时。虽然你可以手动选择使用这个搜索类型，但是这么做基本上不会有什么效果
    + `dfs_query_then_fetch`和`dfs_query_and_fetch`  
    dfs搜索类型有一个预查询的阶段，它会从全部相关的分片里取回项目频数来计算全局的项目频数。我们将在relevance-isbroken(相关性被破坏)里进一步讨论这个
    + `scan`  
    scan(扫描)搜索类型是和scroll(滚屏)API连在一起使用的，可以高效地取回巨大数量的结果。它是通过禁用排序来实现的

#### 3. 扫描和滚屏(scan-and-scroll)  
+ `scan`(扫描)搜索类型是和`scroll`(滚屏)API一起使用来从Elasticsearch里高效地取回巨大数量的结果而不需要付出深分页的代价
1. 为了使用scan-and-scroll(扫描和滚屏)，需要执行一个搜索请求，将`search_type`设置成`scan`，并且传递一个`scroll`参数来告诉Elasticsearch滚屏应该持续多长时间
```
GET /old_index/_search?search_type=scan&scroll=1m <1>保持滚屏开启1分钟
{
  "query": { "match_all": {}},
  "size": 1000
}
```
2. 这个请求的应答没有包含任何命中的结果，但是包含了一个Base-64编码的`_scroll_id`(滚屏id)字符串。现在我们可以将`_scroll_id`传递给`_search/scroll`末端来获取第一批结果
```
GET /_search/scroll?scroll=1m <1>保持滚屏开启另一分钟
<2>_scroll_id 可以在body或者URL里传递，也可以被当做查询参数传递
{
  "scroll_id": "c2NhbjszOzIzOlp6OUZtMTZSU0J1ZDJmSnVxcmk1b0E7MjE6aFI5V0hUV0tSQUs0WVo3UjdHWUxJdzsyNjp1VHpJaFk5UlN6T1dNenpVWGN2RmFROzE7dG90YWxfaGl0czoxNTs="
}
```
3. 滚屏请求也会返回一个新的`_scroll_id`。每次做下一个滚屏请求时，必须传递前一次请求返回的`_scroll_id`
+ 注意，要再次指定`?scroll=1m`。滚屏的终止时间会在我们每次执行滚屏请求时刷新，所以他只需要给我们足够的时间来处理当前批次的结果而不是所有的匹配查询的document。这个滚屏请求的应答包含了第一批次的结果
+ 虽然指定了一个1000的`size`，但是获得了更多的document。当扫描时，size被应用到每一个分片上，所以我们在每个批次里最多或获得`size * number_of_primary_shards`(`size*主分片数`)个document
+ 如果没有更多的命中结果返回，就处理完了所有的命中匹配的document
    
### 九、索引管理

#### 1. 创建删除索引
1. 创建索引
```
PUT /my_index
{
  "settings": { ... any settings ... },
  "mappings": {
    "type_one": { ... any mappings ... },
    "type_two": { ... any mappings ... },
    ...
  }
```
    + 可以通过在config/elasticsearch.yml中添加下面的配置来防止自动创建索引`action.auto_create_index: false`
2. 删除索引
    + 使用以下的请求来删除索引`DELETE /my_index`
    + 也可以用下面的方式删除多个索引`DELETE /index_one,index_two` `DELETE /index_*`
    + 甚至可以删除所有索引`DELETE /_all`

#### 2. 索引设置
1. 可以创建只有一个主分片，没有复制分片的小索引(`number_of_shards`定义一个索引的主分片个数，默认值是`5`。这个配置在索引创建后不能修改)
```
PUT /my_temp_index
{
  "settings": {
    "number_of_shards" : 1,
    "number_of_replicas" : 0
  }
}
```
2. 然后，我们可以用update-index-settings API动态修改复制分片个数
```
PUT /my_temp_index/_settings
{
  "number_of_replicas": 1
}
```

#### 3. 配置分析器
1. 第三个重要的索引设置是`analysis`部分，用来配置已存在的分析器或创建自定义分析器来定制化你的索引
2. `standard`分析器是用于全文字段的默认分析器，对于大部分西方语系来说是一个不错的选择。它考虑了以下几点
    + `standard`分词器，在词层级上分割输入的文本
    + `standard`表征过滤器，被设计用来整理分词器触发的所有表征(但是目前什么都没做)
    + `lowercase`表征过滤器，将所有表征转换为小写
    + `stop`表征过滤器，删除所有可能会造成搜索歧义的停用词，如`a` `the` `and` `is`(停用词过滤器默认是被禁用的)
3. 创建一个基于`standard`分析器的自定义分析器，并且设置`stopwords`参数可以提供一个停用词列表，或者使用一个特定语言的预定停用词列表
4. 在下面的例子中，我们创建了一个新的分析器，叫做`es_std`，并使用预定义的西班牙语停用词
```
PUT /spanish_docs
{
  "settings": {
    "analysis": {
      "analyzer": {
        "es_std": {
          "type": "standard",
          "stopwords": "_spanish_"
        }
      }
    }
  }
}
```
5. `es_std`分析器不是全局的，它仅仅存在于我们定义的`spanish_docs`索引中。为了用analyze API来测试它，我们需要使用特定的索引名
```
GET /spanish_docs/_analyze?analyzer=es_std
El veloz zorro marrón
```
6. 下面简化的结果中显示停用词`El`被正确的删除了  
```
{
  "tokens" : [
    { "token" : "veloz", "position" : 2 },
    { "token" : "zorro", "position" : 3 },
    { "token" : "marrón", "position" : 4 }
  ]
}
```

#### 4. 自定义分析器
1. 分析器是三个顺序执行的组件的结合
    1. 字符过滤器(char_filter)：
        + `html_strip`字符过滤器可以删除所有的HTML标签，并且将HTML实体转换成对应的Unicode字符
    2. 分词器(tokenizer)：
        + `standard`分词器将字符串分割成单独的字词，删除大部分标点符号
        + `keyword`分词器输出和它接收到的相同的字符串，不做任何分词处理
        + `whitespace`分词器只通过空格来分割文本
        + `pattern`分词器可以通过正则表达式来分割文本
    3. 表征过滤器(filter)：
        + `lowercase`表征过滤器将所有表征转换为小写
        + `stop`表征过滤器删除所有可能会造成搜索歧义的停用词，如a the and is
        + `stemmer`表征过滤器将单词转化为他们的根形态(root form)
        + `ascii_folding`表征过滤器会删除变音符号，比如从très转为tres
        + `ngram`和`edge_ngram`表征过滤器可以让表征更适合特殊匹配情况或自动完成
2. 创建自定义分析器的格式
```
PUT /my_index
{
  "settings": {
    "analysis": {
      "char_filter": { ... custom character filters ... },
      "tokenizer": { ... custom tokenizers ... },
      "filter": { ... custom token filters ... },
      "analyzer": { ... custom analyzers ... }
    }
  }
}
```
3. 创建自定义字符过滤器
```
"char_filter": {
  "&_to_and": {
    "type": "mapping",
    "mappings": [ "&=> and "]
  }
}
```
4. 创建自定义表征过滤器
```
"filter": {
  "my_stopwords": {
    "type": "stop",
    "stopwords": [ "the", "a" ]
  }
}
```
5. 组合成分析器
```
"analyzer": {
  "my_analyzer": {
    "type": "custom",
    "char_filter": [ "html_strip", "&_to_and" ],
    "tokenizer": "standard",
    "filter": [ "lowercase", "my_stopwords" ]
  }
}
```
6. 用下面的方式可以将以上请求合并成一条
```
PUT /my_index
{
  "settings": {
    "analysis": {
      "char_filter": {
        "&_to_and": {
          "type": "mapping",
          "mappings": [ "&=> and "]
        }
      },
      "filter": {
        "my_stopwords": {
          "type": "stop",
          "stopwords": [ "the", "a" ]
        }
      },
      "analyzer": {
        "my_analyzer": {
          "type": "custom",
          "char_filter": [ "html_strip", "&_to_and" ],
          "tokenizer": "standard",
          "filter": [ "lowercase", "my_stopwords" ]
        }
      }
    }
  }
}
```
7. 测试分析器`GET /my_index/_analyze?analyzer=my_analyzer  The quick & brown fox`
8. 使用分析器
```
PUT /my_index/_mapping/my_type
{
  "properties": {
    "title": {
      "type": "string",
      "analyzer": "my_analyzer"
    }
  }
}
```

#### 5. 类型和映射
1. Lucene中一个文档由一组简单的键值对组成，一个字段至少需要有一个值，但是任何字段都可以有多个值。类似的，一个单独的字符串可能在分析过程中被转换成多个值。Lucene不关心这些值是字符串，数字或日期，所有的值都被当成不透明字节
2. Elasticsearch类型是在这个简单基础上实现的。一个索引可能包含多个类型，每个类型有各自的映射和文档，保存在同一个索引中
    + 因为Lucene没有文档类型的概念，每个文档的类型名被储存在一个叫`_type`的元数据字段上。当我们搜索一种特殊类型的文档时，Elasticsearch简单的通过`_type`字段来过滤出这些文档
    + Lucene同样没有映射的概念。映射是Elasticsearch将复杂JSON文档映射成Lucene需要的扁平化数据的方式
3. 预防类型陷阱
    + 想象一下我们的索引中有两种类型：blog_en表示英语版的博客，blog_es表示西班牙语版的博客。两种类型都有title字段，但是其中一种类型使用english分析器，另一种使用spanish分析器
    + 我们在两种类型中搜索title字段，首先需要分析查询语句，Elasticsearch会采用第一个被找到的title字段使用的分析器，这对于这个字段的文档来说是正确的，但对另一个来说却是错误的
    + 我们可以通过给字段取不同的名字来避免这种错误。比如，用title_en和title_es。或者在查询中明确包含各自的类型名
    ```
    GET /_search
    {
      "query": {
        "multi_match": {
          "query": "The quick brown fox",
          "fields": [ "blog_en.title", "blog_es.title" ]
        }
      }
    }
    ```
    + 为了保证你不会遇到这些冲突，建议在同一个索引的每一个类型中，确保用同样的方式映射同名的字段

#### 6. 根对象
1. 映射的最高一层被称为根对象，它可能包含下面几项
    + 一个`properties`节点，列出了文档中可能包含的每个字段的映射
    + 多个元数据字段，每一个都以下划线开头，例如`_type` `_id`和`_source`
    + 设置项，控制如何动态处理新的字段，例如`analyzer` `dynamic_date_formats`和`dynamic_templates`
    + 其他设置，可以同时应用在根对象和其他`object`类型的字段上，例如`enabled` `dynamic`和`include_in_all`
2. 文档字段和属性的三个最重要的设置
    + `type`：字段的数据类型，例如string和date
    + `index`：字段是否应当被当成全文来搜索(analyzed)，或被当成一个准确的值(not_analyzed)，还是完全不可被搜索(no)
    + `analyzer`：确定在索引和或搜索时全文字段使用的分析器
    
#### 7. 元数据中的source字段
1. 默认情况下，Elasticsearch用JSON字符串来表示文档主体保存在`_source`字段中。像其他保存的字段一样，`_source`字段也会在写入硬盘前压缩
2. 可以用下面的映射禁用`_source`字段
```
PUT /my_index
{
  "mappings": {
    "my_type": {
      "_source": {
      "enabled": false
      }
    }
  }
}
```
3. 在搜索请求中你可以通过限定`_source`字段来请求指定字段
```
GET /_search
{
  "query": { "match_all": {}},
  "_source": [ "title", "created" ]
}
```
4. 在Elasticsearch中，单独设置储存字段不是一个好做法。完整的文档已经被保存在`_source`字段中。通常最好的办法会是使用`_source`参数来过滤你需要的字段

#### 8. 元数据中的all字段
1. 如果你决定不再使用`_all`字段，你可以通过下面的映射禁用它
```
PUT /my_index/_mapping/my_type
{
  "my_type": {
    "_all": { "enabled": false }
  }
}
```
2. 通过`include_in_all`选项可以控制字段是否要被包含在`_all`字段中，默认值是`true`。在一个对象上设置`include_in_all`可以修改这个对象所有字段的默认行为
3. 你可能想要保留`_all`字段来查询所有特定的全文字段。相对于完全禁用`_all`字段，你可以先默认禁用`include_in_all`选项，而选定字段上启用`include_in_all`
```
PUT /my_index/my_type/_mapping
{
  "my_type": {
    "include_in_all": false,
    "properties": {
      "title": {
        "type": "string",
        "include_in_all": true
      },
      ...
    }
  }
}
```
4. 谨记`_all`字段仅仅是一个经过分析的string字段。它使用默认的分析器来分析它的值，而不管这值本来所在的字段指定的分析器。而且像所有string类型字段一样，你可以配置`_all`字段使用的分析器
```
PUT /my_index/my_type/_mapping
{
  "my_type": {
    "_all": { "analyzer": "whitespace" }
  }
}
```

#### 9. 元数据中的id字段
1. 文档唯一标识由四个元数据字段组成
    + `_id`：文档的字符串ID
    + `_type`：文档的类型名
    + `_index`：文档所在的索引
    + `_uid`：`_type`和`_id`连接成的`type#id`
2. `_id`字段有一个你可能用得到的设置：`path`设置告诉Elasticsearch它需要从文档本身的哪个字段中生成`_id`
```
PUT /my_index
{
  "mappings": {
    "my_type": {
      "_id": {
        "path": "doc_id"
      },
      "properties": {
        "doc_id": {
          "type": "string",
          "index": "not_analyzed"
        }
      }
    }
  }
}
```
3. 虽然这样很方便，但是注意它对`bulk`请求有个轻微的性能影响。处理请求的节点将不能仅靠解析元数据行来决定将请求分配给哪一个分片，而需要解析整个文档主体

#### 10. 动态映射
1. 当Elasticsearch遇到一个未知的字段时，它通过动态映射来确定字段的数据类型且自动将该字段加到类型映射中
2. 可以通过`dynamic`设置来控制这些行为，它接受下面几个选项
    + `true`：自动添加字段(默认)
    + `false`：忽略字段
    + `strict`：当遇到未知字段时抛出异常
3. `dynamic`设置可以用在根对象或任何`object`对象上。你可以将`dynamic`默认设置为`strict`，而在特定内部对象上启用它
```
PUT /my_index
{
  "mappings": {
    "my_type": {
      "dynamic": "strict",
      "properties": {
        "title": { "type": "string"},
        "stash": {
          "type": "object",
          "dynamic": true
        }
      }
    }
  }
}
```
4. 将`dynamic`设置成`false`完全不会修改`_source`字段的内容。`_source`将仍旧保持你索引时的完整JSON文档。然而，没有被添加到映射的未知字段将不可被搜索

#### 11. 自定义动态映射
1. 当Elasticsearch遇到一个新的字符串字段时，它会检测这个字段是否包含一个可识别的日期，比如`2014-01-01`。如果它看起来像一个日期，这个字段会被作为`date`类型添加，否则，它会被作为`string`类型添加
2. 日期检测可以通过在根对象上设置`date_detection`为`false`来关闭
```
PUT /my_index
{
  "mappings": {
    "my_type": {
      "date_detection": false
    }
  }
}
```
3. Elasticsearch判断字符串为日期的规则可以通过`dynamic_date_formats`配置来修改
4. 使用`dynamic_templates`，你可以完全控制新字段的映射，你设置可以通过字段名或数据类型应用一个完全不同的映射  
(es:字段名以`_es`结尾需要使用`spanish`分析器 en:所有其他字段使用`english`分析器)
```
PUT /my_index
{
  "mappings": {
    "my_type": {
      "dynamic_templates": [
        { "es": {
          "match": "*_es",
          "match_mapping_type": "string",
          "mapping": {
            "type": "string",
            "analyzer": "spanish"
          }
        }},
        { "en": {
          "match": "*",
          "match_mapping_type": "string",
          "mapping": {
            "type": "string",
            "analyzer": "english"
          }
        }}
      ]
}}}
```
5. `match_mapping_type`允许你限制模板只能使用在特定的类型上，就像由标准动态映射规则检测的一样，(例如string和long)
6. `match`参数只匹配字段名，`path_match`参数则匹配字段在一个对象中的完整路径，所以`address.*.name`规则将匹配一个这样的字段
```
{
  "address": {
    "city": {
      "name": "New York"
    }
  }
}
```
7. `unmatch`和`path_unmatch`规则将用于排除未被匹配的字段

#### 12. 默认映射
1. 通常，一个索引中的所有类型具有共享的字段和设置。用`_default_`映射来指定公用设置会更加方便，而不是每次创建新的类型时重复操作
2. `_default`映射像新类型的模板。所有在`_default_`映射 之后的类型将包含所有的默认设置，除非在自己的类型映射中明确覆盖这些配置
```
PUT /my_index
{
  "mappings": {
    "_default_": {
      "_all": { "enabled": false }
    },
    "blog": {
      "_all": { "enabled": true }
    }
  }
}
```
3. `_default_`映射也是定义索引级别的动态模板的好地方

#### 13. 重新索引数据
1. 虽然你可以给索引添加新的类型，或给类型添加新的字段，但是你不能添加新的分析器或修改已有字段。假如你这样做，已被索引的数据会变得不正确而你的搜索也不会正常工作
2. 修改在已存在的数据最简单的方法是重新索引：创建一个新配置好的索引，然后将所有的文档从旧的索引复制到新的上
3. `_source`字段的一个最大的好处是你已经在Elasticsearch中有了完整的文档，你不再需要从数据库中重建你的索引，这样通常会比较慢
4. 为了更高效的索引旧索引中的文档，使用`scan-scoll`来批量读取旧索引的文档，然后将通过`bulk API`来将它们推送给新的索引
```
GET /old_index/_search?search_type=scan&scroll=1m
{
  "query": {
    "range": {
      "date": {
        "gte": "2014-01-01",
        "lt": "2014-02-01"
      }
    }
  },
  "size": 1000
}
```

#### 14. 索引别名
1. 前面提到的重新索引过程中的问题是必须更新你的应用，来使用另一个索引名。索引别名正是用来解决这个问题的
2. 有两种管理别名的途径：`_alias`用于单个操作，`_aliases`用于原子化多个操作
3. 创建一个索引`my_index_v1`，然后将别名`my_index`指向它
```
PUT /my_index_v1
PUT /my_index_v1/_alias/my_index
```
4. 检测这个别名指向哪个索引，或哪些别名指向这个索引
```
GET /*/_alias/my_index
GET /my_index_v1/_alias/*
```
5. 两者都将返回下列值
```
{
  "my_index_v1" : {
    "aliases" : {
      "my_index" : { }
    }
  }
}
```
6. 然后，我们决定修改索引中一个字段的映射。当然我们不能修改现存的映射，索引我们需要重新索引数据。首先，我们创建有新的映射的索引`my_index_v2`
```
PUT /my_index_v2
{
  "mappings": {
    "my_type": {
      "properties": {
        "tags": {
          "type": "string",
          "index": "not_analyzed"
        }
      }
    }
  }
}
```
7. 然后我们从将数据从`my_index_v1`迁移到`my_index_v2`(原子化操作)
```
POST /_aliases
{
  "actions": [
    { "remove": { "index": "my_index_v1", "alias": "my_index" }},
    { "add": { "index": "my_index_v2", "alias": "my_index" }}
  ]
}
```
8. 在应用中使用别名而不是索引。然后你就可以在任何时候重建索引。别名的开销很小，应当广泛使用

### 十、分片内部原理

#### 1. 使文本可被搜索
+ 传统的数据库每个字段存储单个值，但这对全文检索并不够。文本字段中的每个单词需要被搜索，对数据库意味着需要单个字段有索引多个值的能力(单词)
+ 最好的支持一个字段多个值需求的数据结构是倒排索引(inverted-index)。倒排索引包含一个有序列表，列表包含所有文档出现过的不重复词项，对于每一个词项，包含了它所有曾出现过文档的列表
+ 早期的全文检索会为整个文档集合建立一个很大的倒排索引并将其写入到磁盘。一旦新的索引就绪，旧的就会被其替换，这样最近的变化便可以被检索到

#### 2. 不变性
+ 倒排索引被写入磁盘后是不可改变的，它永远不会修改，不变性有重要的价值
    1. 不需要锁。如果你从来不更新索引，你就不需要担心多进程同时修改数据的问题
    2. 一旦索引被读入内核的文件系统缓存，便会留在哪里，由于其不变性。只要文件系统缓存中还有足够的空间，那么大部分读请求会直接请求内存，而不会命中磁盘。这提供了很大的性能提升
    3. 其它缓存(像filter缓存)，在索引的生命周期内始终有效。它们不需要在每次数据改变时被重建，因为数据不会变化
    4. 写入单个大的倒排索引允许数据被压缩，减少磁盘I/O和需要被缓存到内存的索引的使用量
+ 一个不变的索引也有不好的地方。如果你需要让一个新的文档可被搜索，你需要重建整个索引。这要么对一个索引所能包含的数据量造成了很大的限制，要么对索引可被更新的频率造成了很大的限制

#### 3. 动态更新索引
+ 通过增加新的补充索引来反映新近的修改，而不是直接重写整个倒排索引。每一个倒排索引都会被轮流查询到，从最早的开始，​查询完后再对结果进行合并
+ Elasticsearch基于Lucene，一个Lucene索引我们在Elasticsearch称作分片，一个Elasticsearch索引是分片的集合。一个Lucene索引包含一个提交点和三个段，Lucene这个java库引入了按段搜索的概念，每一段本身都是一个倒排索引，但索引在Lucene中除表示所有段的集合外，还增加了一个列出了所有已知段的文件(提交点)
+ 当一个查询被触发，所有已知的段按顺序被查询。词项统计会对所有段的结果进行聚合，以保证每个词和每个文档的关联都被准确计算。这种方式可以用相对较低的成本将新文档添加到索引
+ 段是不可改变的，所以既不能从把文档从旧的段中移除，也不能修改旧的段来进行反映文档的更新。取而代之的是，每个提交点会包含一个`.del`文件，文件中会列出这些被删除文档的段信息
+ 当一个文档被删除或被更新时(相当于旧版本文档被删除)实际上只是在`.del`文件中被标记删除。一个被标记删除的文档仍然可以被查询匹配到，但它会在最终结果被返回前从结果集中移除(在后续操作中被标记删除的文档最终会被系统移除)

#### 4. 近实时搜索
+ 新文档在几分钟之内即可被检索，但这样还是不够快，磁盘在这里成为了瓶颈，我们需要的是一个更轻量的方式来使一个文档可被搜索
+ 在Elasticsearch和磁盘之间是文件系统缓存。在内存索引缓冲区(在内存缓冲区中包含了新文档的Lucene索引)中的文档会被写入到一个新的段中(缓冲区的内容已经被写入一个可被搜索的段中，但还没有进行提交)
+ 新段会被先写入到文件系统缓存(这一步代价会比较低)，稍后再被刷新到磁盘(​这一步代价比较高)。不过只要文件已经在缓存中，就可以像其它文件一样被打开和读取了
+ 在Elasticsearch中，写入和打开一个新段的轻量的过程叫做`refresh`。默认情况下每个分片会每秒自动刷新一次。这就是为什么我们说Elasticsearch是近实时搜索(文档的变化并不是立即对搜索可见，但会在一秒之内变为可见)
+ 尽管刷新是比提交轻量很多的操作，它还是会有性能开销。当写测试的时候，手动刷新很有用，但是不要在生产环境下每次索引一个文档都去手动刷新。相反，你的应用需要意识到Elasticsearch的近实时的性质，并接受它的不足
1. 手动刷新所有的索引 `POST /_refresh`
2. 手动刷新`blogs`索引 `POST /blogs/_refresh`
3. 设置自动刷新的时间间隔(`refresh_interval`需要一个持续时间值，例如`1s`或`2m`。一个绝对值`1`表示的是1毫秒，无疑会使你的集群陷入瘫痪)
```
PUT /my_logs
{
  "settings": {
    "refresh_interval": "30s"
  }
}
```
4. 关闭自动刷新(在生产环境中，当你正在建立一个大的新索引时，可以先关闭自动刷新，待开始使用该索引时，再把它们调回来)
```
PUT /my_logs/_settings
{ "refresh_interval": -1 }
```

#### 5. 持久化变更
+ 如果没有用fsync把数据从文件系统缓存刷(flush)到硬盘，我们不能保证数据在断电甚至是程序正常退出之后依然存在。为了保证Elasticsearch的可靠性，需要确保数据变化被持久化到磁盘
+ 即使通过每秒刷新(refresh)实现了近实时搜索，我们仍然需要经常进行完整提交来确保能从失败中恢复。我们也不希望丢失掉两次提交之间发生变化的文档数据
+ Elasticsearch增加了一个translog，或者叫事务日志，在每一次对Elasticsearch进行操作时均进行了日志记录。通过translog，整个流程看起来是下面这样：
1. 一个文档被索引之后，就会被添加到内存缓冲区，并且追加到了translog
2. 刷新(refresh)使分片处于刷新(refresh)完成后，缓存被清空，但是事务日志不会
3. 这个进程继续工作，更多的文档被添加到内存缓冲区和追加到事务日志
4. 分片每30分钟被自动刷新(flush)，或者在translog太大的时候也会刷新。一个新的translog被创建，并且一个全量提交被执行
+ translog提供所有还没有被刷到磁盘的操作的一个持久化纪录。当Elasticsearch启动的时候，它会从磁盘中使用最后一个提交点去恢复已知的段，并且会重放translog中所有在最后一次提交后发生的变更操作
+ translog也被用来提供实时CRUD。当你试着通过ID查询、更新、删除一个文档，它会在尝试从相应的段中检索之前，首先检查translog任何最近的变更。这意味着它总是能够实时地获取到文档的最新版本
1. 手动刷新`blogs`索引 `POST /blogs/_flush`
2. 手动刷新所有索引，并在其完成后返回结果 `POST /_flush?wait_for_ongoing`
3. 设置translog的fsync间隔(提升一些性能，但是有丢失几秒数据的风险)(默认每次写请求完成后都fsync，默认参数`"index.translog.durability": "request"`)
```
PUT /my_index/_settings
{
  "index.translog.durability": "async",
  "index.translog.sync_interval": "5s"
}
```

#### 6. 段合并
+ 由于自动刷新流程每秒会创建一个新的段，这样会导致短时间内的段数量暴增。而段数目太多会带来较大的麻烦(每一个段都会消耗文件句柄、内存和cpu运行周期)，每个搜索请求都必须轮流检查每个段，段越多，搜索也就越慢
+ Elasticsearch通过在后台进行段合并来解决这个问题。小的段被合并到大的段，然后这些大的段再被合并到更大的段。段合并的时候会将那些旧的已删除文档从文件系统中清除。被删除的文档(或被更新文档的旧版本)不会被拷贝到新的大段中
+ 启动段合并不需要你做任何事，进行索引和搜索时会自动进行。合并大的段需要消耗大量的I/O和CPU资源，如果任其发展会影响搜索性能。Elasticsearch在默认情况下会对合并流程进行资源限制，所以搜索仍然有足够的资源很好地执行
+ 手动合并索引为一个段(可能会消耗很多资源使节点不能正常使用) `POST /logstash-2014-10/_optimize?max_num_segments=1`