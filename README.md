# heimaxiuxiu
用于学习测试使用 可以美颜。
#Android基础day14-Fragment&动画
***
##1. Fragment简介（*）

注意：如果考虑Fragment的兼容性，那么一定使用v4包下的API。


###1.1 常见的bug
1. 在Activity的布局文件中内置Fragment时没有指定id错误

		09-26 01:41:43.887: E/AndroidRuntime(1648): Caused by: java.lang.IllegalArgumentException: Binary XML file line #15: Must specify unique android:id, android:tag, or have a parent with an id for com.example.fragmentjunior.FragmentA
2. 在Activity中集成v4包下的Fragment时，就不能直接使用Activity了，必须使用FragmentActivity

	09-26 01:46:14.510: E/AndroidRuntime(1710): Caused by: java.lang.ClassCastException: com.example.fragmentjunior.FragmentA cannot be cast to android.app.Fragment
3. 同一个Fragment对象，只能被添加一次，如果想添加多次，每次new一个全新的（没意思）。

	09-26 02:27:04.858: E/AndroidRuntime(1880): java.lang.IllegalStateException: Fragment already added: FragmentB{b6494320 #1 id=0x7f080001 FragmentB}

##2. Fragment的基本使用（*****）
1. 自定义一个Fragment继承Fragment
2. 覆写Fragment的onCreateView方法，在该方法中让Fragment跟布局文件绑定
3. 将Fragment添加到Activity
	1. Declare the fragment inside the activity's layout file.
	2. Or, programmatically add the fragment to an existing ViewGroup.

##3. Fragment的生命周期（***）
1. 第一种集成方式的生命周期
	1. Fragment的生命周期跟Activity的生命周期一致的
2. 第二种集成方式的生命周期
	1. 当new Fragment的时候是不走任何生命周期方法
	2. 只有当transaction.commit();了，才会走生命周期方法

##4. Fragment的切换（案例-QQ）（*****）
1. 通过replace方法切换

2. 通过先add之后的hide或者show方法

##5. Fragment之间的通信（调用）（****）
ImageView的scaleType属性：
android:scaleType="matrix" ： 默认图片多大就直接放到左上角。

android:scaleType="fitXY" ： 非等比例拉伸，让x和y方向正好跟ImageView的宽和高匹配

android:scaleType="fitStart" 等比例拉伸，先把图片放到ImageView的左上角，然后开始往右下角等比例拉伸，直到有一个边正好匹配搭配ImageView上就停止。 

android:scaleType="fitEnd"：等比例拉伸，先把图片放到ImageView的右下角，然后开始往左上角等比例拉伸，直到有一个边正好匹配搭配ImageView上就停止。 

android:scaleType="fitCenter"：等比例拉伸，等比例拉大到正好友一个边跟ImageView匹配，然后把整个图片居中。

android:scaleType="center" ： 按图片的原来size居中显示，当图片长/宽超过View的长/宽，则截取图片的居中部分显示

android:scaleType="centerInside" ： 将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长/宽等于或小于View的长/宽

android:scaleType="centerCrop" ： 按比例扩大图片的size居中显示，使得图片长(宽)等于或大于View的长(宽) 


1. 在Fragment中如何获取Context对象
	1. MainActivity activity = (MainActivity)getActivity();
2. 在LeftFragment中获取跟LeftFragment绑定的同一个Activity的RightFragment
		
		FragmentManager fragmentManager = getFragmentManager();
	  	RightFragment rightFragment = (RightFragment) fragmentManager.findFragmentByTag("right");


##6. Android中的动画（**）
1. (逐)帧动画
	1. 步骤：
		1. 在res目录下创建帧动画资源文件（xml）

				<?xml version="1.0" encoding="utf-8"?>
					<animation-list xmlns:android="http://schemas.android.com/apk/res/android" >
    				<item android:drawable="@drawable/g11" android:duration="200"></item>
				</animation-list>
		2. 将帧动画资源设置给IamgeView控件
			1. android:src="@drawable/frame_anim"
		3. 播放动画资源
			1. 首先获取到ImageView的帧动画的对象AnimationDrawable
			2. 然后调用AnimationDrawable的方法
	
	
2. 补间（tween animation）动画 Animation 
	注意：在Android中所有的View类都可以执行补间动画
	注意：补间动画改变的是控件的表象形式，并没有真正改变控件的属性。
	1. （透明度）渐变效果
		1. 纯代码的形式


				AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
				alphaAnimation.setDuration(2000);//2秒执行时间
				//让IamgeView执行该动画
				iv.startAnimation(alphaAnimation);

		2. xml文件的形式
			1. 在res/anima/xxxx.xml
			2. 使用AnimationUtils将动画资源转换为Animation对象
			3. iv.startAnimation(alphaAnimation);
			
	2. 平移
	3. 缩放
	4. 旋转
	5. 集合

3. 属性（值）动画

核心类：Animator
	1. （透明度）渐变效果
		1. 纯代码形式
		2. xml文件
	2. 平移
	3. 缩放
	4. 旋转
	5. 集合



