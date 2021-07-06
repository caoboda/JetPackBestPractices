package com.tq.paging;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/** Paging
 * Paging是Google为了方便方便分页加载而设计的一个组件，它为几种常见的分页机制提供了统一的解决方案，让我们只关注业务代码
 *
 * Paging支持的架构类型
 * 1、网络数据->手机
 * 2、数据库->手机
 * 3、网络数据->数据库->手机
 *  网络
 * 对网络数据进行分页加载是最常见的一种分页需求后台对分页机制所设计的API接口通常也不太一样，总体可分为3种：
 * 1、Paging组件提供了3种不同的方案，以应对不同的分页机制,它们分别是PositionalDataSource、PageKeyedDataSource、ItemKeyedDataSource
 *
 *  数据库
 * 熟悉了网络数据分页之后、数据库分页就是对数据源进行替换
 *
 * 网络+数据库
 * 出于对用户体验的考虑，我们一般对网络数据列表进行缓存，以便用户在下次打开应用程序时，应用程序可以优先展示缓存数据，我们通常会利用数据库对网络数据进行缓存，所以我们需要同时
 * 处理好网络和数据库这两个数据源。多数据源会使得业务逻辑变得复杂，所以我们使用单数据源作为解决方案。即从网络回去的数据直接缓存进数据库，列表只从数据库这个唯一的数据源获取数据，
 * 这里使用BoundryCallBack
 *
 * Paging工作原理
 * 见图Paging工作原理.png
 *
 * Paging3个核心类
 * PageListAdapter
 * RecycleView 需要搭配适配器使用，如果使用Paging组件，适配器需要继承PageListAdapter
 * PagedList
 * PagedList通知DataSource何时获取数据，以及如何获取数据，比如，何时加载第一页/下一页，第一页加载的数量，提前多少条数据开启预加载等，从DataSource中获取的数据将存储在PagedList中
 * DataSource
 * 1、在DataSource中执行具体的数据载入工作，数据可以来自网络也可以来自数据库。根据分页机制的不同Paging为我们提供了3种DataSource
 * 2、数据的载入需要在工作线程中进行
 *
 * PositionalDataSource适用于任意位置加载数据，且目标数据源数量固定的情况 ，如：请求时携带参数为start=2&count=5，表示向服务器请求从第二条数据开始往后的5条数据
 * PageKeyedDataSource适用于数据源以页的方式进行请求的情况，如请求携带的参数为page=2&pageSize=5，表示数据源以5条数据为一页，当前返回第二页的5条数据。
 * ItemKeyedDataSource适用于当前目标数据的下一页需要依赖上一页数据的最后一个对象中的某个字段作为key的情况，常见于评论，如上一页的最后一个对象的key为10001，
 * 那么在下一页请求时要携带的参数since=10001&pageSize=10，则服务器会返回key=10001之后的5条数据。
 *
 * Paging组件除了单纯地支持网络、数据库为数据源外，还支持网络+数据库的架构方式，这就用到了BoundaryCallback
 *
 * 数据库是页面的唯一数据来源：页面(ViewModel中的LiveData)订阅了数据库的变化，当数据库中的数据发生变化时，会直接反映到页面上。
 *  BoundaryCallback的使用流程如下：见图BoundaryCallback的使用流程.png
 *  1.若数据库中没有数据，会通知BoundaryCallBack中的onZeroItemsLoaded()加载第一页数据，若数据库中有数据，
 *  当RecycleView滑动到列表底部时，且数据库中的数据全部加载完毕时，会通知BoundaryCallBack中的onItemAtEndLoaded()加载下一页数据。
 *  2.当BoundaryCallBack中的回调方法（onZeroItemsLoaded或onItemAtEndLoaded）被调用时，需要在该方法内开启工作线程请求网络数据。
 *  3.当网络数据成功加载回来后，并不直接展示数据，而是开启工作线程将其写入数据库。
 *  4.由于页面(ViewModel中的LiveData)订阅了数据库的变化，当数据库有新的数据变化(写入、删除、更改）時，会自动同步更新到页面。
 *  5.当需要刷新数据时，通过下拉刷新功能在页面下拉过程中清空数据。当数据库被清空时，由于数据库发生变化，进而再次触发步骤1，通知BoundaryCallback重新获取数据。
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
