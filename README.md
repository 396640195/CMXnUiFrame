### **一.CMXnUIFrame介绍**

>demo下载地址: https://github.com/396640195/CMUiFrame/tree/uiframe1.1

  **CMXnUIFrame** 为Common  XiaoNiu UI Frame 的简称(**下文简称为UIFrame**),  针对安卓应用UI开发的共性进行了封装, 界面的主要组成部分有:Header,Top,Center,Bottom,Dialog,ErrorShow, 一个完整的移动端app的界面基本全都是由这些部分组成,  所有元素当然是可选. 那么CMXnUiFrame针对这些界面元素进行了抽象封装,主要有这么一些层级管理器:
  - **头部布局管理** HeaderLayoutManager  管理界面头部布局
  - **顶部布局管理** TopLayoutManager  管理界面顶部布局
  - **中间内容布局管理** CenterLayoutManager  管理界面中间内容区域视图布局
  - **中间覆盖层布局管理** CenterMaskLayoutManager 管理中间内容视图覆盖布局,用来实现特殊场景展示信息
  - **对话框布局管理** DialogLayoutManager 对话框布局管理
  - **全屏布局管理** FullScreenLayoutManager  全屏信息展示布局管理类似DialogLayoutManager



>**在此框架之下开发UI, 有如下几个优点:**

- 1.可以大大提升界面开发效率,很多重复工作可以写一次整个项目复用
- 2.在开发之初就已经为你做了最大布局层级优化,提升了应用效率;
- 3.抽象了应用层常用的场景, 可以动态定制界面元素;
- 4.你只需给定一个你关心的最小单元的layout布局文件即可, 且不影响布局层级; 
      layout可以是一个View,不一定是容器;
- 5.所有布局是add到容器中，界面的所有layout处于同一层级;


### **二.UI开发之基类**
  UIFrame提供了两个基类,你可根据实际需求继承基中一个即可.
  - **1. UIFrameBasicActivity**
  - **2. UIFrameBasicFragment**
  
```java
public abstract class UIFrameBasicActivity extends FragmentActivity implements
        IBasicViewAdapter,
        IViewCommonBehavior,
        OnRefreshListener
{
        //Fragment用法一样,请下载demo参照例子,这里只拿Activity举例;
}
```

  这两个基类都实现了如下几个接口:
  - **1.IBasicViewAdapter**  提供界面的基本元素, 按需实现这个适配器的接口,就可生成你想要的界面;
  - **2.IViewCommonBehavior** 该接口定义了界面具有哪些行为能力,我们可以具体看一下这个接口定义:
  
### **三.快速搭建一个界面**

#### **3.1 继承UI基类**

> **基本常用的接口实现介绍**

```java
public class BasicSimpleActivity extends UIFrameBasicActivity {
    @Override
    public boolean isNeedEventBus() {
        return false;  //默认不需要使用EventBus,如果要用EventBus,重写该方法返回true即可;
    }
    
    @Override
    public void onLeftHeaderClicked() {
        //头部左边点击事件处理
    }

    @Override
    public void onRightHeaderClicked() {
       //头部右边点击事件处理
    }
    
    @Override
    public void onAllViewConstructed() {
        //所有布局加载完成后处理ui逻辑
    }
    
        @Override
    public void onRefresh() {
        //下拉刷新处理
    }

    @Override
    public void onLoadMore() {
        //加载更多处理
    }
}
```
#### **3.2 设置界面的统一背景**

> **使用场景: 每个界面的内容区域会与背景有一个margin值,这个时候能看到容器的背景色,此时需要有统一背景色,可以使用如下api:**

```java
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
         this.setContainerBackgroundColor(R.color.ui_frame_ripple_color);
         //也可以调用这个接口
         //this.setContainerBackgroundResource(R.drawable.icon_background);
         
    }
```



#### **3.3 添加一个头部元素**

> **使用场景: 每个界面都有一个头部视图, 左边有返回按钮或图标, 中音有标题文字, 右边有可能是图片 也有可能是文字; 此时可以添加一个HeaderLayoutManager**

```java
    @Override
    public HeaderLayoutManager addHeaderView(IContainerManager container) {
    
        //使用自定义的布局文件
        //HeaderLayoutManager hlm = HeaderLayoutManager.buildLayoutManager(container, R.layout.layout_header);
        
        //使用默认ui_frame_common_header_layout的布局
        HeaderLayoutManager hlm = HeaderLayoutManager.buildLayoutManager(container);
        
        //设置图标的大小间距,0.8f为图标缩放比例.
        hlm.setHeaderLeftImage(R.mipmap.arrow_left_normal,0.8f);
        
        //所有布局管理都可以拿到View视图对象
        //View view = hlm.getContentView();
        //当有多个视图层的时候可以这样,当然这样拿到视图的集合用处不大.
        //Viwe views = hlm.getContentViews(); 
        
        //可以这样分别拿到某一个视图对象
        //这里只是举例说明,只有在DialogLayoutManager,FullScreenLayoutManager才可以这样添加多个布局;
        // View view = hlm.addLayout(R.layout.layout_header)
        
        //设置头部分隔线颜色
        hlm.setHeaderLineColor(R.color.colorAccent);
        return hlm;
    }
```

> **头部布局需要遵守几个约定,你才可以使用框架关于头部视图操作的API:**

-  必需的: 1.头部布局是三个TextView, 用来实现左中右三个部分的内容;
-  必需的: 2.左中右TextView的id命名约定, 
ui_frame_header_left,
ui_frame_header_center,
ui_frame_header_right;

> **UIFrame层已定义了一个 ui_frame_common_header_layout.xml布局内容如下.**
> **默认使用的是这个xml,如果不能满足你的需求,你可以自已定制.  若想使用框架的api,需要遵守上面两个约定;**

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:background="#FFFFFF"
    >

    <TextView
        android:id="@+id/ui_frame_header_left"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:text="返回"
        android:layout_marginLeft="10dp"
        android:drawablePadding="10dp"
        android:layout_alignParentLeft="true"
        android:textColor="#000000"
        android:gravity="center" />

    <TextView
        android:id="@+id/ui_frame_header_center"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="标题"
        android:textColor="#000000"
        android:layout_centerInParent="true"
        android:gravity="center" />

    <TextView
        android:id="@+id/ui_frame_header_right"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_alignParentRight="true"
        android:text="More"
        android:layout_marginRight="10dp"
        android:textColor="#000000"
        android:gravity="center" />
</RelativeLayout>

```

> **遵守上面约定后,就可以使用IHeaderViewBehavior中的接口.**

例如:
```java
    @Override
    public HeaderLayoutManager addHeaderView(IContainerManager container) {
    
        HeaderLayoutManager hlm = HeaderLayoutManager.buildLayout(container, R.layout.layout_header);
        //在当前方法中, 只能这样使用,其它方法类似.
        hlm.setHeaderLeftImage(R.mipmap.arrow_left_normal);
        
        return  hlm;
    }
```

> **IHeaderViewBehavior.java 接口的具体定义如下:**

```java
public interface IHeaderViewBehavior {
    /**
     * 设置头部左边的文字
     * @param resource 字符资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderLeftText(@StringRes int resource);

    /**
     * 设置头部左边的文字
     * @param content 字符
     * @return 返回当前的TextView对象
     */
    TextView setHeaderLeftText(String content);

    /**
     * 设置头部左边的图片资源
     * @param resource 图片资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderLeftImage(@DrawableRes  int resource);

    /**
     * 设置头部中间的文字内容
     * @param resource 字符资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderCenterText(@StringRes int resource);

    /**
     * 设置头部中间的文字内容
     * @param content 字符
     * @return 返回当前的TextView对象
     */
    TextView setHeaderCenterText(String content);

    /**
     * 设置头部右边的文字内容
     * @param resource 字符资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderRightText(@StringRes int resource);

    /**
     * 设置头部右边的文字内容
     * @param content 字符
     * @return 返回当前的TextView对象
     */
    TextView setHeaderRightText(String content);

    /**
     * 设置头部右边的图片资源
     * @param resource 图片资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderRightImage(@DrawableRes  int resource);

    /**
     * 设置头部视图的点击事件
     * @param lister
     */
    void setOnHeaderClickLister(HeaderLayoutManager.OnHeaderViewClickListener lister);
}


```

> 修改头部布局样式

```xml

    <style name="ui_frame_header_left_text_style">
        <item name="android:layout_width">wrap_content</item>
        <item name="android:layout_height">50dp</item>
        <item name="android:background">#FFFFFF</item>
        <item name="android:textColor">#000000</item>
        <item name="android:textSize">15sp</item>
        <item name="android:drawablePadding">11dp</item>
        <item name="android:layout_marginLeft">11dp</item>
    </style>

    <style name="ui_frame_header_center_text_style">
        <item name="android:layout_width">wrap_content</item>
        <item name="android:layout_height">50dp</item>
        <item name="android:background">#FFFFFF</item>
        <item name="android:textColor">#000000</item>
        <item name="android:textSize">15sp</item>
        <item name="android:drawablePadding">13dp</item>
    </style>

    <style name="ui_frame_header_right_text_style">
        <item name="android:layout_width">wrap_content</item>
        <item name="android:layout_height">50dp</item>
        <item name="android:background">#FFFFFF</item>
        <item name="android:textColor">#000000</item>
        <item name="android:textSize">15sp</item>
        <item name="android:drawablePadding">13dp</item>
        <item name="android:layout_marginRight">10dp</item>
    </style>

    <style name="ui_frame_header_container_style">
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:background">#FFFFFF</item>
    </style>

```

#### **3.4 添加一个顶部视图**

> **使用场景: 在头部视图之下,有时候需要展示一个通知或广告,你可以利用这个固定位置来做这个功能; 也可以用它来实现其它固定位置的需求和功能;**

```java
       @Override
    public TopLayoutManager addTopView(IContainerManager container) {
        TopLayoutManager tlm = TopLayoutManager.buildLayoutManager(container, R.layout.layout_top);
        //顶部视图只能添加一个视图,可以直接调用api进行显隐控制;
        tlm.setVisibility(View.GONE);
        return tlm;
    }
    
    public void onClick(View v){
        //对通知广告的区域作动画显示, 所有层级都可以这样实现一个动画,你也可以扩展动画功能;
        animateY(ElementView.TopView,1000);
    }
```

> **布局文件内容如下:**

```xml
<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="50dp"
    android:layout_gravity="center"
    android:background="#FF864A"
    android:gravity="center"
    android:textColor="#000000"
    android:padding="10dp"
    android:text="今日新闻:青海首次发现雪豹 两只幼崽都已半个月大小" />

```


####  **3.5添加一个底部视图**

> **使用场景: 一般在界面底部有几个Tab页,可用使用这个布局管理实现. 它是一个固定位置不变的和Top,Header类似;**

```java
    @Override
    public BottomLayoutManager addBottomView(IContainerManager container) {
    
        BottomLayoutManager blm = BottomLayoutManager.buildLayout(container, R.layout.layout_simple_bottom);
        //可以通过布局管理器获得视图对象
        View view = blm.getContentView();
        //底部布局只能添加一个视图,可以直接调用api进行显隐控制;
        blm.setVisibility(View.GONE);
         
        return blm;
    }
```


#### **3.6 添加一个中间视图**

> **使用场景: 每个界面离不开内容展示,所以这个区域几乎是少不了. CenterLayoutManager提供了两个静态方法来构造中间视图**
- buildGeneralLayoutManager(container,layout) 这个是指定一个布局,你可以自由撑控
- buildPullRefreshLayoutWithListView(container) 这个是框架定义好了一个带有pullrefresh和listView功能的中间视图

```java

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        //添加一个指定的中间视图布局,完全由你撑控;
        CenterLayoutManager clt = CenterLayoutManager.buildGeneralLayoutManager(container, R.layout.layout_center);
        return clt;
    }
    
```

> **布局文件内容如下,这个视图添加了一个pull refresh功能的容器,一个ScrollView滚动支持的容器**

```xml

<?xml version="1.0" encoding="utf-8"?>
<com.xn.uiframe.widget.UIFrameRefreshViewLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <TextView
                android:layout_width="match_parent"
                android:layout_height="500dp"
                android:background="#c0c0c0"
                android:gravity="center"
                android:text="Center View 1"
                android:textColor="#999009"
                android:textSize="22sp" />


            <TextView
                android:layout_width="match_parent"
                android:layout_height="500dp"
                android:background="#c0c0c0"
                android:gravity="center"
                android:text="Center View 2"
                android:textColor="#999009"
                android:textSize="22sp" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="500dp"
                android:background="#c0c0c0"
                android:gravity="center"
                android:text="Center View 3"
                android:textColor="#999009"
                android:textSize="22sp" />
        </LinearLayout>


    </ScrollView>
</com.xn.uiframe.widget.UIFrameRefreshViewLayout>

```

> **开启pullRefresh功能**

```java
    @Override
    public void onRefresh() {
        //uiframe封装了EventBusProxy的方法,同时增加了这下面静态方法
        EventBusProxy.dispatcherOnMainThreadDelay(new Runnable() {
            @Override
            public void run() {
                mMask01.setVisibility(View.VISIBLE);
                mMask02.setVisibility(View.VISIBLE);
                animateY(ElementView.CenterMaskView, Easing.EasingAnimation.EaseInOutQuart, 1000);
                stopRefresh(true);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        EventBusProxy.dispatcherOnMainThreadDelay(new Runnable() {
            @Override
            public void run() {
                stopLoadMore(true);
            }
        }, 2000);
    }

```

> **添加一个中间视图带listView,同时带伴随视图的用法**

```java
//伴随视图必需在有PullRefresh功能下才有效;
    @Override
    public void addCompanionScrollableHeader(CenterLayoutManager container) {
        container.addCompanionScrollableHeader(R.layout.layout_companion_header);
    }

    @Override
    public void addCompanionScrollableFooter(CenterLayoutManager container) {
        container.addCompanionScrollableFooter(R.layout.layout_companion_footer);
    }

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        //该方法会返回一个带有ListView,PullRefresh功能的视图给你,同时你可以添加伴随视图;你不需要传入layout文件,默认使用框架中自带布局文件;
        CenterLayoutManager clm = CenterLayoutManager.buildPullRefreshLayoutWithListView(container);
        return clm;
    }
```

> **添加完伴随视图之后对listview进行处理**

```java

  @Override
    public void onCompanionViewAddFinished(CenterLayoutManager container) {
    
        ListView listview = container.getListView();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("测试的" + i);
        }
        listview.setAdapter(new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, list));

    }


```

#### **3.7 添加一个中间视图的遮罩层**

> **使用场景: 这种视图层用来实现网络请求无数据,请求异常在中间内容区域展示一些提示信息的场景. 这个层级也不限制View的个数;**

```java

    @Override
    public CenterMaskLayoutManager addCenterMaskView(IContainerManager container) {
    
        CenterMaskLayoutManager clt = CenterMaskLayoutManager.buildLayoutManager(container);
        mMask01 = clt.addLayout(R.layout.layout_center_mask01);
        mMask02 = clt.addLayout(R.layout.layout_center_mask02);
        
        //只有一个mask视图可以用这个方法,多个的时需要单独立操作如下持有视图
        //clt.setVisibility(View.GONE);
        mMask01.setVisibility(View.GONE);
        mMask02.setVisibility(View.GONE);

        mMask01.setOnClickListener(this);
        mMask02.setOnClickListener(this);

        return clt;
    }
    
```

#### **3.8 添加一个全屏对话框**

> **使用场景: 这种全屏视图用来替代对话框的效果,比直接使用Dialog要方便易掌控;**

```java

    //最好使用一个DialogViewHoder把Dialog的逻辑从Activity中拆分出去,UI中只保持主线逻辑;
    private View mDialog01, mDialog02; 
    
    //可以添加多个对话框视图,理论上不限制Dialog的视图个数;
    @Override
    public DialogLayoutManager addDialogView(IContainerManager container) {
        DialogLayoutManager fsm = DialogLayoutManager.buildLayoutManager(container);

        //有多个视图时需要单独操控,你只需每次addLayout的时候保持View的引用就可以后面自由操控该视图;
        mDialog01 = fsm.addLayout(R.layout.layout_dialog_01);
        mDialog01.findViewById(R.id.ok_button_of_dialog_01).setOnClickListener(this);
        mDialog01.setVisibility(View.GONE);

        mDialog02 = fsm.addLayout(R.layout.layout_dialog_02);
        mDialog02.findViewById(R.id.ok_button_of_dialog_02).setOnClickListener(this);
        mDialog02.setVisibility(View.GONE);

        return fsm;
    }
    
```

#### **3.9 添加一个全屏视图**

```java

    View fullScreen01, fullScreen02;

    @Override
    public FullScreenLayoutManager addExtraFullScreenView(IContainerManager container) {
    
        //这种全屏视图用来实现菊花加载进度,或者全屏广告覆盖在内容之上的场景;
        FullScreenLayoutManager fullScreenLayoutManager = FullScreenLayoutManager.buildLayoutManager(container);
        
        fullScreen01 = fullScreenLayoutManager.addLayout(R.layout.layout_full_screen_01);
        fullScreen02 = fullScreenLayoutManager.addLayout(R.layout.layout_full_screen_02);
        
        fullScreen01.setOnClickListener(this);
        fullScreen02.setOnClickListener(this);
        
        return fullScreenLayoutManager;
        
    }

```

#### 3.9Fragment的支持

> 如果要使用fragment碎片,api已封装了相关的方法,可以直接使用;

```java

public class SimplePullRefreshActivity extends BasicSimpleActivity {

    private SimpleFragment mHomeFragment;
    private ListFragment mSetFragment;
    private GeneralFragment mAccountFragment;
    
    @Override
    public void onTabSelected(int index) {
        if (mHomeFragment == null) {
            mHomeFragment = new SimpleFragment();
            Bundle bundleHome = new Bundle();
            bundleHome.putString("content", "From 首页");
            mHomeFragment.setArguments(bundleHome);
            //如果fagment还没有添加调用此api
            addUIFrameFragment(mHomeFragment);
        }else{
            //如果fagment已经添加调用此api
            changeUIFragment(mHomeFragment);
        }
    }

    }

```

### **四. 动画支持**

> **动画支持不够强大,但能满足基本的显示隐匿动画,后续再扩展;**

**动画接口的使用**
```java

 //activity实现了这个接口,可以直接调用
 animateX(ElementView.CenterView, Easing.EasingAnimation.EaseOutBounce, 1500);
 animateY(ElementView.BottomView, Easing.EasingAnimation.EaseInQuart, 500);
 
 //如果该层级视图有多个view,那它对可见的视图View都起作用;
 animateY(ElementView.DialogView, Easing.EasingAnimation.EaseInQuart, 500);
 
```

**动画能力的接口定义**

```java

public interface IAnimateBehavior {
    /**
     * 针对该视图进行Y轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param duration
     */
    void animateY(ElementView elementCategory, long duration);

    /**
     * 针对该视图进行X轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param duration
     */
    void animateX(ElementView elementCategory, long duration);

    /**
     * 针对该视图进行XY轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param xDuration        x轴动画时间
     * @param yDuration        y轴动画时间
     */
    void animateXY(ElementView elementCategory, long xDuration, long yDuration);

    /**
     * 针对该视图进行Y轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param easing          动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param duration
     */
    void animateY(ElementView elementCategory, Easing.EasingAnimation easing, long duration);

    /**
     * 针对该视图进行X轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param easing          动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param duration
     */
    void animateX(ElementView elementCategory, Easing.EasingAnimation easing, long duration);

    /**
     * 针对该视图进行XY轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param easing          动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param xDuration        x轴动画时间
     * @param yDuration        y轴动画时间
     */
    void animateXY(ElementView elementCategory, Easing.EasingAnimation easing, long xDuration, long yDuration);
}


```

> **每个LayoutManager都可以调用如下api对View执行动画**

```java

    /**
     * 针对该视图进行Y轴动画
     *
     * @param duration 动画时间
     */
    void animateY(long duration);

    /**
     * 针对该视图进行X轴动画
     *
     * @param duration 动画时间
     */
    void animateX(long duration);

    /**
     * 针对该视图进行XY轴动画
     *
     * @param xDuration x轴动画时间
     * @param yDuration y轴动画时间
     */
    void animateXY(long xDuration, long yDuration);

    /**
     * 针对该视图进行Y轴动画
     *
     * @param easing   动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param duration
     */
    void animateY(Easing.EasingAnimation easing, long duration);

    /**
     * 针对该视图进行X轴动画
     *
     * @param easing   动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param duration
     */
    void animateX(Easing.EasingAnimation easing, long duration);

    /**
     * 针对该视图进行XY轴动画
     *
     * @param easing    动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param xDuration x轴动画时间
     * @param yDuration y轴动画时间
     */
    void animateXY(Easing.EasingAnimation easing, long xDuration, long yDuration);

```

### 五. EventBusProxy工具类定义

```java

public class EventBusProxy {

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    /**
     * 主线程中简单执行一个任务
     * @param task
     */
    public static void dispatcherOnMainThread(Runnable task){
        sHandler.post(task);
    }

    /**
     * 主线程中简单执行一个延迟任务
     * @param task
     * @param duration
     */
    public static void dispatcherOnMainThreadDelay(Runnable task,long duration){
        sHandler.postDelayed(task,duration);
    }
    /**
     * 发送一个Action的消息,接收该消息的类需要注册订阅EventBus消息;
     *
     * @param action
     */
    public static void dispatcher(Action action) {
        EventBus.getDefault().post(action);
    }

    /**
     * 延迟发送消息，用来替代Handler的postDelay方法.
     *
     * @param action
     * @param secondsDelay
     */
    public static void dispatherDelay(final Action action, long secondsDelay) {
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBusProxy.dispatcher(action);
            }
        }, secondsDelay);
    }

    /**
     * 定义一个通用的消息基类
     *
     * @param <T>
     */
    public static class Action<T> {
        public int action;
        public String extra;
        public T t;

        /**
         * 只需要一个类型的时候调用该构造方法
         **/
        public Action(int action) {
            this.action = action;
        }

        /**
         * 需要附带一个字符串的时候调用该构造方法
         **/
        public Action(int action, String extra) {
            this(action);
            this.extra = extra;
        }

        /**
         * 当一个Integer,String还不构需要带一个复杂对象的时候调用该方法
         **/
        public Action(int action, String extra, T t) {
            this(action, extra);
            this.t = t;
        }

    }

    /**
     * 注册订阅EventBus消息
     * @param subscriber
     */
    public static void register(Object subscriber){
        EventBus.getDefault().register(subscriber);
    }
    /**
     * 反注册订阅EventBus消息
     * @param subscriber
     */
    public static void unregister(Object subscriber){
        EventBus.getDefault().unregister(subscriber);
    }
}


```