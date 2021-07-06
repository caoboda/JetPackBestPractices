### Jetpack最佳实践

[toc]



### 1、**Jetpack**

#### What:什么是Jetpack

Jetpack是一个由多个库组成的套件，可帮助开发者遵循最佳做法，减少样本代码并编写可用在各种Android版本和设备中一致运行的代码，让开发者集中精力编写重要的代码。

#### **Why:为什么使用Jetpack**

**1、遵循最佳做法**

Android Jetpack采用最新的设计方法构建，具有向后兼容性，可以减少崩溃和内存泄露。

**2、消除样板代码**

Android Jetpack可以管理各种繁琐的Activity(如：生命周期管理、导航、后台任务等)，以便打造出出色的应用。

**3、减少不一致**

Jetpack所包含的这些库可以在各种Android版本的设备中以一致的方式运作，减少代码不兼容性的问题。

#### **Jetpack和AndroidX**

1、AndroidX命名空间中包含Jetpack库。

2、AndroidX用来替代Android Support Library库(v4,v7兼容包)。

3、AAC(Android Architecture Component一个处理UI的生命周期与数据的持久化的架构) 中的组件并入AndroidX。

4、一些需要频繁更新和迭代的特性也并入AndroidX ，Google对我们的SDK问题进行修复后放在Android Support Library 和AAC中，现在统一放在AndroidX中。 

### **2、LifeCycle**

#### What:什么是LifeCycle

LifeCycle是 2107 年 google 大会推出来的，它属于 architecture compoment 里面的一个组件，它可以用来检查 Activity 的生命周期，而不必强依赖 Activity。

#### Why:为什么使用LifeCycle即LifeCycle好处：

1、帮助开发者建立可感知生命周期的组件

2、组件在其内部管理自己的生命周期，从而降低模块耦合度

3、降低内存泄漏发生的可能性

4、Activity、Fragment、Sevice、Application都有自己的LifeCycle支持

### **3、ViewModel**

#### 为什么会需要（诞生）ViewModel？

1、瞬态数据的丢失(如：输入框的数据在横竖屏旋转之后丢失)。

2、异步调用时的内存泄露(如：网络异步请求时 Activity销毁了而网络请求的对象还存在内存中，无法被GC回收，导致内存泄露)。

3、类膨胀和提高维护和测试的难度

#### ViewModel的作用

1、它是介于View(视图)和Model(数据模型)之间的桥梁。

2、使View(视图)和Model(数据模型)进行分离，同时通过ViewModel进行通讯。

#### ViewMode解決内存泄漏

ViewModel是一个抽象类，其中只有一个方法**onCleared()**，当ViewModel不再被需要的时候，也就是与之相关的Activity都被销毁时，该方法会被系统调用，我们可以在这个方法里面执行一些资源释放的操作，以免内存泄漏。

既然ViewModel的销毁是由系统来判断和执行的，那么系统是如何判断的呢？是根据Context引用。因此，我们在使用ViewModel的时候，千万不能从外面传入Activity，Fragment或者View之类的含有Context引用的东西，否则系统会认为该ViewModel还在使用中，从而无法被系统销毁回收，导致内存泄漏的发生。但如果你希望在ViewModel中使用Context怎么办？我们可以使用**AndroidViewModel**类，它继承自ViewModel，并且接收**Application**作为Context，既然是Application作为Context，也就意味着，我们能够明确它的生命周期和Application是一样的，这就不算是一个内存泄露了。

### 4、LiveData

#### LiveData作用

在我们ViewModel数据变化时通知界面。

#### LiveData优点

1、确保界面是最新的数据状态。
2、不会发生内存泄漏。
3、不会因Activity停止而发生崩溃。
4、不需要手动处理生命周期。
5、数据始终保持最新状态。
6、适当的配置更改(屏幕旋转)不会导致数据的变化
7、共享资源

### 5、DataBinding

#### DataBinding的意义

让布局文件承担部分原本属于页面的工作，降低页面和布局的耦合度。

#### DataBinding的优势

1、不需要findViewById，项目更加简洁可读性更高

2、布局文件里面可编写简单的业务逻辑

### 6、Room

What ：就是在SQLite数据库上提供了一层封装。

### 7、Navigation

####  Navigation诞生的原因

平时一般使用FragmentManager和FragmentTranstion来管理Fragment的切换，Navigation的出现是为了方便我们管理Fragment和App Bar

####  Navigation的主要元素

1、Navigation Graph 是一种新的xml资源文件，包含应用程序所有的页面，及页面之间的关系。

2、NavigationHostFragment 是一个特殊的Fragment,可以看作其他Fragment的容器，Navigation Graph中的Frgment通过NavigationHostFragment进行展示。

3、NavController 用于在代码中完成NNavigation Graph中具体页面的切换工作。

4、三者之间的关系 使用NavController进行页面的切换，告诉它你要去Navigation Graph中的哪个Fragment，NavController将你想去的Fragment展示在NavigationHostFragment中。

### 8、Paging

#### Paging是什么

Paging是Google为了方便方便分页加载而设计的一个组件，它为几种常见的分页机制提供了统一的解决方案，让我们只关注业务代码。

#### Paging支持的架构类型

1、网络数据->手机

2、数据库->手机

3、网络数据->数据库->手机

##### 网络

对网络数据进行分页加载是最常见的一种分页需求后台对分页机制所设计的API接口通常也不太一样，总体可分为3种：

1、Paging组件提供了3种不同的方案，以应对不同的分页机制,它们分别是PositionalDataSource、PageKeyedDataSource、ItemKeyedDataSource

##### 数据库

熟悉了网络数据分页之后、数据库分页就是对数据源进行替换


##### 网络+数据库

出于对用户体验的考虑，我们一般对网络数据列表进行缓存，以便用户在下次打开应用程序时，应用程序可以优先展示缓存数据，我们通常会利用数据库对网络数据进行缓存，所以我们需要同时

处理好网络和数据库这两个数据源。多数据源会使得业务逻辑变得复杂，所以我们使用单数据源作为解决方案。即从网络回去的数据直接缓存进数据库，列表只从数据库这个唯一的数据源获取数据，这里使用BoundryCallBack来实现，页面(ViewModel中的LiveData)订阅了数据库的变化，当数据库中的数据发生变化时，会直接反映到页面上。。

BoundaryCallback的使用流程图如下：

![BoundaryCallback的使用流程](C:\Users\W10\Desktop\BoundaryCallback的使用流程.png)

1.若数据库中没有数据，会通知BoundaryCallBack中的onZeroItemsLoaded()加载第一页数据，若数据库中有数据，

当RecycleView滑动到列表底部时，且数据库中的数据全部加载完毕时，会通知BoundaryCallBack中的onItemAtEndLoaded()加载下一页数据。

2.当BoundaryCallBack中的回调方法（onZeroItemsLoaded或onItemAtEndLoaded）被调用时，需要在该方法内开启工作线程请求网络数据。

3.当网络数据成功加载回来后，并不直接展示数据，而是开启工作线程将其写入数据库。

4.由于页面(ViewModel中的LiveData)订阅了数据库的变化，当数据库有新的数据变化(写入、删除、更改）時，会自动同步更新到页面。

5.当需要刷新数据时，通过下拉刷新功能在页面下拉过程中清空数据。当数据库被清空时，由于数据库发生变化，进而再次触发步骤1，通知BoundaryCallback重新获取数据。


#### Paging工作原理图

![Paging工作原理](C:\Users\W10\Desktop\Paging工作原理.png)

#### Paging3个核心类

##### 1、PageListAdapter

RecycleView 需要搭配适配器使用，如果使用Paging组件，适配器需要继承PageListAdapter

##### 2、PagedList

PagedList通知DataSource何时获取数据，以及如何获取数据，比如，何时加载第一页/下一页，第一页加载的数量，提前多少条数据开启预加载等，从DataSource中获取的数据将存储在PagedList中

##### 3、DataSource

1、在DataSource中执行具体的数据载入工作，数据可以来自网络也可以来自数据库。根据分页机制的不同Paging为我们提供了3种DataSource

2、数据的载入需要在工作线程中进行

PositionalDataSource适用于任意位置加载数据，且目标数据源数量固定的情况 ，如：请求时携带参数为start=2&count=5，表示向服务器请求从第二条数据开始往后的5条数据

PageKeyedDataSource适用于数据源以页的方式进行请求的情况，如请求携带的参数为page=2&pageSize=5，表示数据源以5条数据为一页，当前返回第二页的5条数据。

ItemKeyedDataSource适用于当前目标数据的下一页需要依赖上一页数据的最后一个对象中的某个字段作为key的情况，常见于评论，如上一页的最后一个对象的key为10001，那么在下一页请求时要携带的参数since=10001&pageSize=10，则服务器会返回key=10001之后的5条数据。

### WorkManager

#### 诞生背景

在后台执行任务的需要是很常见的，Android提供了多种方式，如JobScheduler、Loader、Service，若这些API没有正确的使用，则可能消耗大量的电量。

#### 作用

WorkManager为应用程序中那些不需要及时完成的任务提供了统一的解决方案，以便在用户体验和电量之间达到一个平衡的效果。

#### 特点

##### 1、针对的是不需要及时完成的任务

如发送日志、同步应用程序数据、备份数据，这些任务一般都不需要立即完成，如果我们自己来完成这些任务，

逻辑可能会很复杂，若API使用不当，可能导致消耗大量电量。它不能保证任务立刻执行。

##### 2、任务一定会被执行

WorkManager能保证任务一定会被执行，即使应用程序不在执行，甚至在设备重启后，任务仍然会在合适的时刻执行，因为WorkManager有自己的数据库，任务的所有信息和数据都保存在数据库中，因此只要任务交给了WorkManager，哪怕是应用程序彻底退出，或者设备重启，WorkManager仍然能够保证任务的执行。

##### 3、兼容范围广

支持API Level 14(Android 4.0版本),不需要安装Google Play Services,而JobScheduler是Android从5.0增加支持一种特殊的机制,即任务调度，因此兼容性更好。

#### WorkManager的兼容方案

WorkManager能根据设备的情况，选择不同的执行方案API Level 21(Android 5.0)以上的设备通过JobScheduler完成任务，API Level 21(Android 5.0)以下的设备通过AlarmManager和Broadcast Receives组合来完成任务，无论采用哪种方式，都是通过Executor来执行的。



