# google开源库flexbox-layout使用demo

![](http://upload-images.jianshu.io/upload_images/7426378-4a6abff821004d06.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 简介：

github地址如下：
> [flexbox-layout](https://github.com/google/flexbox-layout)


### 使用

#### gridle添加依赖

> compile 'com.google.android:flexbox:0.3.0'

#### xml使用

```
<com.google.android.flexbox.FlexboxLayout
        android:id="@+id/flexBoxLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:alignContent="stretch"
        app:alignItems="stretch"
        app:flexDirection="row"
        app:flexWrap="wrap"
        app:justifyContent="flex_start"
        app:showDividerHorizontal="beginning|middle">

    </com.google.android.flexbox.FlexboxLayout>
```

#### 动态添加子view

```
  //设置交叉轴起点对齐
        flexBoxLayout.setAlignContent(FLEX_START);

        //控制副轴的对齐方式
        flexBoxLayout.setAlignItems(FLEX_START);

        //设置主轴方向
        flexBoxLayout.setFlexDirection(ROW);

        //设置换行
        flexBoxLayout.setFlexWrap(WRAP_REVERSE);

        //设置元素主轴方向上的对齐方式
        flexBoxLayout.setJustifyContent(FLEX_END);

        //设置水平方向分割线
        flexBoxLayout.setShowDividerHorizontal(DividerItemDecoration.VERTICAL);

        for (int i = 0; i < 15; i++) {
            textView = new TextView(this);
            textView.setText(i + "just贝贝" + (i * i));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(20, 5, 20, 5);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundColor(Color.YELLOW);
            flexBoxLayout.addView(textView);
        }
        ViewGroup.LayoutParams params = textView.getLayoutParams();

        if (params instanceof FlexboxLayout.LayoutParams) {
            FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
            //子view的属性
            layoutParams.setFlexBasisPercent(0.5f);
            layoutParams.setAlignSelf(FLEX_START);
            layoutParams.setMinHeight(dpToPixel(20));
            layoutParams.setMinWidth(dpToPixel(40));
        }
    }

     /**
     * px转dp工具类
     * @param pixel
     * @return
     */
    public int pxToDp(int pixel) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return pixel < 0 ? pixel : Math.round(pixel / displayMetrics.density);
    }

    /**
     * dp转px工具类
     * @param dp
     * @return
     */
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return dp < 0 ? dp : Math.round(dp * displayMetrics.density);
    }


    private void initView() {
        flexBoxLayout = (FlexboxLayout) findViewById(R.id.flexBoxLayout);
    }
```

#### 属性详解

##### `flexbox-layout`属性

1.`alignContent`属性控制多根轴线的对齐方式(也就是控制多行，如果子元素只有一行，则不起作用):
```
stretch (default): 默认值，充满交叉轴的高度（测试发现，需要alignItems 的值也为stretch 才有效）。
flex_start: 与交叉轴起点对齐。
flex_end: 与交叉轴终点对齐。
center: 与交叉轴居中对齐。
space_between: 交叉轴两端对齐，中间间隔相等。
space_around: 到交叉轴两端的距离相等。
```

2.`alignItems`性控制元素在副轴方向的对齐方式：

```
stretch (default) ：默认值，如果item没有设置高度，则充满容器高度。
flex_start：顶端对齐
flex_end：底部对齐
center：居中对齐
baseline：第一行内容的的基线对齐。
```

3.`flexDirection`属性决定了主轴的方向，即`FlexboxLayout`里子`Item`的排列方向:

```
row (default)： 默认值，主轴为水平方向，起点在左端，从左到右。
row_reverse：主轴为水平方向，起点在右端，从右到左。
column：主轴为竖直方向，起点在上端，从上到下。
column_reverse：主轴为竖直方向，起点在下端，从下往上。
```

4.`flexWrap` 这个属性决定`flexbox-layout` 容器是单行还是多行，并且决定副轴（与主轴垂直的轴）的方向:

```
nowrap: 不换行，一行显示完子元素。
wrap: 按正常方向换行。
wrap_reverse: 按反方向换行。
```

5.`justifyContent`属性控制元素主轴方向上的对齐方式:

```
flex_start (default): 默认值，左对齐
flex_end: 右对齐
center: 居中对齐
space_between: 两端对齐，中间间隔相同
space_around: 每个元素到两侧的距离相等。
```

6.`showDividerHorizontal`控制显示水平方向的分割线,值为`none `| `beginning` | `middle `| `end` 其中的一个或者多个

##### flexbox-layout子view的属性

1.`layout_order`

```
属性可以改变子元素的排列顺序，默认情况下，`FlexboxLayout`子元素的排列是按照xml文件中出现的顺序。默认值为1，值越小排在越靠前。
```

2.`layout_flexGrow(float)`

```
layout_flexGrow 子元素的放大比例， 决定如何分配剩余空间（如果存在剩余空间的话），默认值为0,不会分配剩余空间，
如果有一个item的 layout_flexGrow 是一个正值，那么会将全部剩余空间分配给这个Item,如果有多个Item这个属性都为正值，
那么剩余空间的分配按照layout_flexGrow定义的比例（有点像LinearLayout的layout_weight属性）。
```

3.`layout_flexShrink(float)：`

```
layout_flexShrink:子元素缩小比例，当空间不足时，子元素需要缩小（设置了换行则无效），默认值为1，
如果所有子元素的layout_flexShrink 值为1,空间不足时，都等比缩小，如果有一个为0，其他为1，空间不足时，为0的不缩小，负值无效。
```

4.`layout_alignSelf`

```
layout_alignSelf 属性可以给子元素设置对齐方式，上面讲的alignItems属性可以设置对齐，这个属性的功能和alignItems一样，只不过alignItems作用于所有子元素，
而layout_alignSelf 作用于单个子元素。默认值为auto, 表示继承alignItems属性，如果为auto以外的值，则会覆盖alignItems属性。：
```

```
auto (default)
flex_start
flex_end
center
baseline
stretch

除了auto以外，其他和alignItems属性一样。
```

5.`layout_flexBasisPercent (fraction)`

```
layout_flexBasisPercent的值为一个百分比，表示设置子元素的长度为它父容器长度的百分比，如果设置了这个值，那么通过这个属性计算的值将会
覆盖layout_width或者layout_height的值。但是需要注意，这个值只有设置了父容器的
长度时才有效（也就是MeasureSpec mode 是 MeasureSpec.EXACTLY）。默认值时-1。
```

6.`layout_minWidth / layout_minHeight (dimension)`

```
强制限制 FlexboxLayout的子元素（宽或高）不会小于最小值，不管layout_flexShrink这个属性的值为多少，子元素不会被缩小到小于设置的这个最小值。
```

7.`layout_maxWidth / layout_maxHeight (dimension)`

```
这个和上面的刚好相反，强制限制FlexboxLayout子元素不会大于这个最大值, 不管layout_flexGrow的值为多少，子元素不会被放大到超过这个最大值
```

8.`layout_wrapBefore`

```
layout_wrapBefore 属性控制强制换行，默认值为false,如果将一个子元素的这个属性设置为true，那么这个子元素将会成为一行的第一个元素。这个属性将忽略flex_wrap 设置的 noWrap值。
```

