# TestMyUtil
## 引用方式

implementation 'com.chtj.mymoduleutil:mymoduleutil:1.0.2'

<dependency>
  <groupId>com.chtj.mymoduleutil</groupId>
  <artifactId>mymoduleutil</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>


###屏幕适配,

BaseActivity,
BaseFragment,
BaseFragmentV4,
BaseFragmentActivity,

可直接进行调用，无需配置

###下拉菜单
NiceSpinner
<com.chtj.util.nicespinner.NiceSpinner
        android:id="@+id/tv_value"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:layout_weight="1"
        android:gravity="center"
        android:text="sdlfjlskdjf"
        android:textColor="@color/content_color"
        android:textSize="12sp" />
        
        
设置数据源
String[] spinnerItem=new String[]{"teacher","student","kids","women"};
niceSpinner.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, spinnerItem));
  

###json转换
-传入json数组返回集合对象
 String jsonStr="[{\"headImage\":\"back_1.png\",\"id\":\"1\",\"introduction\":\"my name is chtj...\",\"name\":\"chtj\",\"nickName\":\"chtj\",\"phone\":\"134-****-2606\",\"pwd\":\"123\"},{\"headImage\":\"back_1.png\",\"id\":\"2\",\"introduction\":\"my name is ctj...\",\"name\":\"ctj\",\"nickName\":\"ctj\",\"phone\":\"136-****-2606\",\"pwd\":\"123\"}]";
 
List<Userinfo> userinfoList =GsonUtil.jsonToArray(jsonStr,Userinfo[].class);
  
  
-传入json字符串返回对象
String jsonBeanStr="{\"headImage\":\"back_1.png\",\"id\":\"3\",\"introduction\":\"my name is zs...\",\"name\":\"zs\",\"nickName\":\"zs\",\"phone\":\"136-***-2606\",\"pwd\":\"123\"}";

Userinfo userinfos=GsonUtil.getBean(jsonBeanStr,Userinfo.class);
  
  
 
