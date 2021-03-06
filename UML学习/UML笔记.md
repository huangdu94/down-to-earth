# UML笔记

### 1. UML概述  
统一建模语言(Unified Modeling Language,UML)是一种为面向对象系统的产品进行说明、可视化和编制文档的一种标准语音，是非专利的第三代建模和规范语言。UML使用面向对象设计的建模工具，但独立于任何具体程序设计语言

### 2. UML主要作用
1. 为软件系统建立可视化模型
2. 为软件系统建立构件
3. 为软件系统建立文档

### 3. UML系统开发中三个主要的模型
1. 功能模型 从用户的角度展示系统的功能。包括用例图
2. 对象模型 采用对象，属性，操作，关联等概念展示系统的结构和基础。包括类别图、对象图
3. 动态模型 展现系统的内部行为。包括序列图、活动图、状态图

### 4. UML组成
UML由模型元素(Model Element)、图(Diagram)、视图(View)和通用机制(General Mechanism)等几个部分组成
1. 模型元素  
	代表面向对象中的类、对象、消息和关系等概念，是构成图的最基本的常面向对象系统分析与设计方法用概念
2. 图  
	是模型元素集的图形表示，通常是由弧(关系)和顶点(其他模型元素)相互连接构成的
3. 视图  
	是表达系统的某一方面的特征的UMI，建模元素的子集，由多个图构成，是在某一个抽象层上，对系统的抽象表示
4. 通用机制  
	用于表示其他信息，比如注释、模型元素的语义等。另外，UMI。还提供扩展机制，使UML语言能够适应一个特殊的方法(或过程)，或扩充至一个组织或用户
		
### 5. UML图例
1. 用例图
	描述角色以及角色与用例之间的连接关系。说明是谁要使用系统，以及他们使用该系统可以做些什么。一个用例图包含了多个模型元素，如系统、参与者和用例，并且显示了这些元素之间的各种关系，如泛化、关联和依赖
2. 类图
	类图是描述系统中的类，以及各个类之间的关系的静态视图。能够让我们在正确编写代码以前对系统有一个全面的认识。类图是一种模型类型，确切地说，是一种静态模型类型。类图表示类、接口和它们之间的协作关系
3. 对象图
	与类图极为相似，它是类图的实例，对象图显示类的多个对象实例，而不是实际的类。它描述的不是类之间的关系，而是对象之间的关系
4. 活动图
	描述用例要求所要进行的活动，以及活动间的约束关系，有利于识别并行活动。能够演示出系统中哪些地方存在功能，以及这些功能和系统中其他组件的功能如何共同满足前面使用用例图建模的商务需求
5. 状态图
	描述类的对象所有可能的状态，以及事件发生时状态的转移条件，可以捕获对象、子系统和系统的生命周期。它可以告知一个对象可以拥有的状态，并且事件(如消息的接收、事件的流逝、错误、条件变为真等)会怎么随着时间的推移来影响这些状态。一个状态图应该连接到所有具有清晰的可标识状态和复杂行为的类；该图可以确定类的行为，以及该行为如何根据当前的状态变化，也可以展示哪些事件将会改变类的对象的状态。状态图是对象图的补充
6. 序列图(顺序图)
	序列图使用来显示参与者如何以一系列顺序的步骤与系统的对象交互的模型。顺序图可以用来展示对象之间是如何进行交互的。顺序图将显示的重点放在消息序列上，即强调消息是如何在对象之间被发送和接收的
7. 协作图
	和序列图相似，显示对象间的动态合作关系。可以看成是类图和顺序图的交集，协作图建模对象或者角色，以及他们彼此之间是如何通信的。如果强调时间和顺序，则使用序列图；如果强调上下级关系，则选择协作图；这两种图合称为交互图
8. 构件图(组件图)
	描述代码构件的物理结构以及各种构件之间的依赖关系。用来建模软件的组件及其相互之间的关系，这些图由构件标记符和构件之间的关系构成。在组件图中，构件时软件单个组成部分，它可以是一个文件，产品、可执行文件和脚本等
9. 部署图(配置图)
	用来建模系统的物理部署。例如，计算机和设备，以及它们之间是如何连接的。部署图的使用者是开发人员、系统集成人员和测试人员。部署图用于表示一组物理结点的集合及结点间的相互关系，从而建立了系统物理层面的模型