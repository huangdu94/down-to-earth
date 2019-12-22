## Elasticsearch权威指南笔记

### 一、概述
+ Elasticsearch是一个实时分布式搜索和分析引擎。它让你以前所未有的速度处理大数据成为可能
+ Elasticsearch是一个基于Apache Lucene(TM)的开源搜索引擎。无论在开源还是专有领域，Lucene可以被认为是迄今为止最先进、性能最好的、功能最全的搜索引擎库

### 二、下载与安装
+ 安装Elasticsearch唯一的要求是安装Java，地址：[java](www.java.com)
+ 你可以从[elasticsearch](elasticsearch.org/download)下载最新版本的Elasticsearch
+ `./bin/elasticsearch -d` 后台运行
+ `curl 'http://localhost:9200/?pretty'` 查看es是否正常运行
+ `curl -XPOST 'http://localhost:9200/_shutdown'` 关闭es

### 三、Cluster、Node、Index、Type、Document和Field
+ Elastic本质上是一个分布式数据库，允许多台服务器协同工作，每台服务器可以运行多个Elastic实例
+ 单个Elastic实例称为一个节点(node)。一组节点构成一个集群(cluster)
+ Elastic会索引所有字段，经过处理后写入一个反向索引(Inverted Index)。查找数据的时候，直接查找该索引。所以，Elastic数据管理的顶层单位就叫做Index(索引)。它是单个数据库的同义词。每个Index(即数据库)的名字必须是小写
+ Index里面单条的记录称为Document(文档)。许多条Document构成了一个Index。Document使用JSON格式表示
+ Document可以分组，这种分组就叫做Type，它是虚拟的逻辑分组，用来过滤Document。不同的Type应该有相似的结构(schema)，举例来说，id字段不能在这个组是字符串，在另一个组是数值。这是与关系型数据库的表的一个区别。性质完全不同的数据(比如products和logs)应该存成两个Index，而不是一个Index里面的两个Type(虽然可以做到)
|关系数据库|数据库|表|行|列|
|:-:|:-:|:-:|:-:|:-:|
|Elasticsearch|索引(Index)|类型(Type)|文档(Documents)|字段(Fields)|

### 四、Java API连接ES
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

### 五、与Elasticsearch交互
+ 基于HTTP协议，以JSON为数据交互格式的RESTful API
+ `curl -X<VERB> <PROTOCOL>://<HOST>/<PORT>?<QUERY_STRING> -d <BODY>`
	+ `VERB` http方法:`GET`,`POST`,`PUT`,`HEAD`,`DELETE`
	+ `PROTOCOL` http或https协议(只有在Elasticsearch前面有https代理的时候可用)
	+ `HOST` Elasticsearch集群中的任何一个节点的主机名，如果是在本地的节点，那么就叫localhost
	+ `PORT` Elasticsearch HTTP服务所在的端口，默认为9200
	+ `QUERY_STRING` 一些可选的查询请求参数，例如`?pretty`参数将使请求返回更加美观易读的JSON数据
	+ `BODY` 一个JSON格式的请求主体(如果请求需要的话)
+ linux利用`curl`命令注意`-d`后面的参数部分有些特殊字符需要转义(例`,`)
+ linux利用`curl`命令，请求体可放在文件中 `curl -XPOST http://localhost:9200/_bulk --data-binary @requests`(bulk API需要，其中requests为请求体文件)
+ 计算集群中的文档数量(简写形式，省略`PROTOCOL` `HOST` `PORT`) 
```
GET /_count
{
	"query": {
		"match_all": {}
	}
}
```

### 六、ES简单教程(ES功能概览)
1. 创建三个员工
```
PUT /megacorp/employee/1
{
	"first_name" : "John",
	"last_name" : "Smith",
	"age" : 25,
	"about" : "I love to go rock climbing",
	"interests": [ "sports", "music" ]
}

PUT /megacorp/employee/2
{
	"first_name" : "Jane",
	"last_name" : "Smith",
	"age" : 32,
	"about" : "I like to collect rock albums",
	"interests": [ "music" ]
} 
	
PUT /megacorp/employee/3
{
	"first_name" : "Douglas",
	"last_name" : "Fir",
	"age" : 35,
	"about": "I like to build cabinets",
	"interests": [ "forestry" ]
}
```
2. 根据id操作员工信息
	+ `GET /megacorp/employee/1` 查
	+ `DELETE /megacorp/employee/1` 删
	+ `PUT /megacorp/employee/1` 改
	+ `HEAD /megacorp/employee/1` 检查某文档是否存在(`curl`使用时加`-i`参数，信息在响应头中)
3. 简单搜索
	+ `GET /megacorp/employee/_search` 默认情况下搜索会返回前10个结果
	+ `GET /megacorp/employee/_search?q=last_name:Smith` 查询字符串(query string)搜索，轻量级搜索
	+ `GET /megacorp/employee/_search?q=age[30 TO 60]&sort=age:desc&from=0&size=2` 查询年龄为30-60之间，降序排序，分页查询
4. 使用DSL(Domain Specific Language,特定领域语言)语句查询
	1. 搜索指定字段值
	```
	GET /megacorp/employee/_search
	{
		"query" : {
			"match" : {
				"last_name" : "Smith"
			}
		}
	}
	```
	2. 搜索范围
	```
	GET /megacorp/employee/_search
	{
		"query" : {
			"filtered" : {
				"filter" : {
					"range" : {
						"age" : { "gt" : 30 } <1>
					}
				},
				"query" : {
					"match" : {
						"last_name" : "smith" <2>
					}
				}
			}
		}
	}
	```
		+ <1>这部分查询属于区间过滤器(range filter),它用于查找所有年龄大于30岁的数据，gt为"greater than"的缩写
		+ <2>这部分查询与之前的match语句(query)一致
	3. 全文搜索
	```
	GET /megacorp/employee/_search
	{
		"query" : {
			"match" : {
				"about" : "rock climbing"
			}
		}
	}
	```
	4. 短语搜索(比全文搜索更具有针对性)
	```
	GET /megacorp/employee/_search
	{
		"query" : {
			"match_phrase" : {
				"about" : "rock climbing"
			}
		}
	}
	```
	5. 高亮搜索结果
	```
	GET /megacorp/employee/_search
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
	6. 分析，聚合查询
	```
	GET /megacorp/employee/_search
	{
		"aggs": {
			"all_interests": {
				"terms": { "field": "interests" }
			}
		}
	}
	```
	7. 聚合和精确查询组合
	```
	GET /megacorp/employee/_search
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
	8. 分级汇总(统计每种兴趣下职员的平均年龄)
	```
	GET /megacorp/employee/_search
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

### 七、分布式集群及分布式存储
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
1. `routing`参数
	+ `shard = hash(routing) % number_of_primary_shards` 计算文档属于哪个分片
	+ `get` `index` `delete` `bulk` `update` `mget` 都接收一个`routing`参数，它用来自定义文档到分片的映射
2. `replication`参数
	+ `replication`复制默认的值是`sync`这将导致主分片得到复制分片的成功响应后才返回
	+ 如果你设置`replication`为`async`，请求在主分片上被执行后就会返回给客户端，它依旧会转发请求给复制节点，但你将不知道复制节点成功与否
	+ 上面的这个选项不建议使用，默认的`sync`复制允许Elasticsearch强制反馈传输，`async`复制可能会因为在不等待其它分片就绪的情况下发送过多的请求而使Elasticsearch过载
3. `consistency`参数
	+ 默认主分片在尝试写入时需要规定数量(quorum)或过半的分片(可以是主节点或复制节点)可用，这是防止数据被写入到错的网络分区
	+ 规定的数量计算公式`int( (primary + number_of_replicas) / 2 ) + 1`
4. `timeout`参数
	+ 当分片副本不足时Elasticsearch会等待更多的分片出现，默认等待一分钟
	+ 可以设置timeout 参数让它终止的更早：100表示100毫秒，30s表示30秒
+ update API接受`routing` `replication` `consistency`和`timout`参数
+ mget API `routing`参数可以被docs中的每个文档设置
+ bulk API还可以在最上层使用`replication`和`consistency`参数，`routing`参数则在每个请求的元数据中使用
  
### 八、文档相关操作
1. 文档
	+ 一个文档不只有数据，它还包含了元数据(metadata):
	+ `_index` 文档存储的地方
	+ `_type` 文档代表的对象的类(每个类型type都有自己的映射mapping或者结构定义， 就像传统数据库表中的列一样)
	+ `_id` 文档的唯一标识
	+ 其它元数据(例: `_version`)
2. 索引一个文档
	+ 指定id	
	```
	PUT /website/blog/123
	{
		"title": "My first blog entry",
		"text": "Still trying this out...",
		"date": "2014/01/01"
	}
	```
	+ 自增id
	```
	POST /website/blog/
	{
		"title": "My second blog entry",
		"text": "Still trying this out...",
		"date": "2014/01/02"
	}
	```
3. 检索文档
	+ `GET /website/blog/123?pretty` 美化输出
	+ `GET /website/blog/123?_source=title,text` 检索文档的指定字段
	+ `GET /website/blog/123/_source` 不要元数据
	+ `curl -XHEAD -i http://localhost:9200/website/blog/123` (检查文档是否存在)
4. 更新整个文档
	+ 文档在Elasticsearch中是不可变的，我们不能修改他们。如果需要更新已存在的文档，可以使用索引文档章节提到的index API重建索引(reindex)
	```
	PUT /website/blog/123
	{
		"title": "My first blog entry",
		"text": "I am starting to get the hang of this...",
		"date": "2014/01/01"
	}
	```
	+ update API似乎允许你修改文档的局部，但事实上Elasticsearch遵循与之前所说完全相同的过程
5. 创建一个新文档
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
6. 删除文档
	+ 删除一个文档也不会立即从磁盘上移除，它只是被标记成已删除。Elasticsearch将会在你之后添加更多索引的时候才会在后台进行删除内容的清理
	+ `DELETE /website/blog/123`
7. Elasticsearch版本控制
	+ 为了保证数据在多线程操作下的准确性ES要进行版本控制(CAS无锁)
	+ 悲观锁和乐观锁
		+ 悲观锁：假设会发生并发冲突，屏蔽一切可能违反数据准确性的操作
		+ 乐观锁：假设不会发生并发冲突，只在提交操作时检查是否违反数据完整性
	+ 内部版本控制和外部版本控制
		+ 内部版本控制：`_version`自增长，修改数据后，`_version`会自动的加1
		+ 外部版本控制：为了保持`_version`与外部版本控制的数值一致
		+ 使用`version_type=external`检查数据当前的version值是否小于请求中的version值
8. 带版本控制的操作
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
9. 文档局部更新(本质上同更新整个文档)
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
10. 检索多个文档(mget API)
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
11. 更省时的批量操作
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

### 九、搜索(基本的工具)
+ 为了充分挖掘Elasticsearch的潜力，需要理解以下三个概念：
	1. Mapping:数据在每个字段中的解释说明
	2. Analysis:全文是如何处理的可以被搜索的
	3. Query DSL:Elasticsearch使用的灵活的、强大的查询语言
1. 空搜索
	+ `GET /_search`
	+ `GET /_search?timeout=10ms`
	+ 需要注意的是`timeout`不会停止执行查询，它仅仅告诉你目前顺利返回结果的节点然后关闭连接。在后台，其他分片可能依旧执行查询，尽管结果已经被发送
2. 多索引和多类别
	+ `/_search` 在所有索引的所有类型中搜索
	+ `/gb/_search` 在索引gb的所有类型中搜索
	+ `/gb,us/_search` 在索引gb和us的所有类型中搜索
	+ `/g*,u*/_search` 在以g或u开头的索引的所有类型中搜索
	+ `/gb/user/_search` 在索引gb的类型user中搜索
	+ `/gb,us/user,tweet/_search` 在索引gb和us的类型为user和tweet中搜索
	+ `/_all/user,tweet/_search` 在所有索引的user和tweet中搜索
3. 分页搜索
	+ `GET /_search?size=5`(`size`默认10)
	+ `GET /_search?size=5&from=5`(`from`跳过开始的结果数，默认0)
	+ 注意深度分页影响性能
4. 简易搜索
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

### 十、映射及分析
+ Elasticsearch为对字段类型进行猜测，动态生成了字段和类型的映射关系。默认字段`_all`是`string`类型
+ 确切值(Exact values)和全文文本(Full text)
	1. 为了方便在全文文本字段中进行这些类型的查询，Elasticsearch首先对文本分析(analyzes)，然后使用结果建立一个倒排索引
	2. Elasticsearch使用一种叫做倒排索引(inverted index)的结构来做快速的全文搜索。倒排索引由在文档中出现的唯一的单词列表，以及对于每个单词在文档中的位置组成
	3. 为了创建倒排索引，我们首先切分每个文档的指定字段为单独的单词(`terms` `tokens`)把所有的唯一词放入列表并排序
	4. 为了找到确实存在于索引中的词，索引文本和查询字符串都要标准化为相同的形式，这个表征化和标准化的过程叫做分词(analysis)
+ 分析和分析器
	+ `GET /megacorp/employee/1/_termvectors?fields=about`(查看指定文档指定字段分词情况)
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
			+ 指定分词器分词
			`POST /_analyze?analyzer=standard {"text": "text content"}`
			+ 使用指定字段分词器分词
			`POST /megacorp/_analyze?field=about {"text": "text content"}`
		+ 指定分析器 当Elasticsearch在你的文档中探测到一个新的字符串字段，它将自动设置它为全文string字段并用standard分析器分析(可以通过映射`mapping`设置)
+ 映射
	1. 核心简单字段类型
	|类型|表示的数据类型|
	|:-:|:-:|
	|String|string|
	|Whole number|byte,short,integer,long|
	|Floating point|float,double|
	|Boolean|boolean|
	|Date|date|
	2. 当你索引一个包含新字段的文档，一个之前没有的字段，Elasticsearch将使用动态映射猜测字段类型，这类型来自于JSON的基本数据类型，使用以下规则
	|JSON type|Field type|
	|:-:|:-:|
	|Boolean: true or false|boolean|
	|Whole number: 123|long|
	|Floating point: 123.45|double|
	|String, valid date: "2014-09-15"|date|
	|String: "foo bar"|string|
	3. 查看映射 `GET /gb/_mapping/tweet` `GET /gb/_mapping`
	4. 自定义字段映射
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
	5. 符合核心字段类型
		1. 多值字段
			+ 数组中所有值必须为同一类型
			+ 创建一个新字段，这个字段索引了一个数组，Elasticsearch将使用第一个值的类型来确定这个新字段的类型
		2. 空字段(这四个字段将被识别为空字段而不被索引)
		```
		"empty_string": "",
		"null_value": null,
		"empty_array": [],
		"array_with_null_value": [null]
		```
		3. 多层对象 Elasticsearch会动态的检测新对象的字段，并且映射它们为`object`类型，将每个字段加到`properties`字段下
			+ json对象
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
			+ mapping映射
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
			+ 内部对象是怎样被索引的
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
			+ 内部对象数组是怎样被索引的
			`{"followers":[{"age":35,"name":"Mary White"},{"age":26,"name":"Alex Jones"},{"age":19,"name":"Lisa Smith"}]`
			`"followers.age": [19, 26, 35], "followers.name": [alex, jones, lisa, smith, mary, white]`