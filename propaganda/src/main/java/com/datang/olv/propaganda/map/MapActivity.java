package com.datang.olv.propaganda.map;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.datang.olv.propaganda.utils.ResourceUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by l on 14-7-21.
 */
public class MapActivity extends FragmentActivity {
    private static final String TAG = "MapActivity";
    private static final String OLV = "olv";
    private static final String LOCS_XML = "locs.xml";
    private static final boolean DEBUG = false;
    SlidingMenu menu;
    BDLocationListener myListener = new MyLocationListener();
    MapView mMapView = null;
    private PopupWindow mPopupSearchWindow;
    private PopupWindow mPopupMenuWindow;
    private LocationClient mLocationClient;
    private Map<String, Set<LatLon>> addLocMap = new HashMap<String, Set<LatLon>>();
    private Map<String, Set<LatLon>> removeLocMap = new HashMap<String, Set<LatLon>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_map);
        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapActivity.this.finish();

            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) this.findViewById(R.id.rl_bmapView);
        //获取地图控件引用
        BaiduMapOptions bo = new BaiduMapOptions()
                .compassEnabled(true).zoomControlsEnabled(false);
        mMapView = new MapView(this, bo);
        relativeLayout.addView(mMapView, 0);


        // configure the SlidingMenu
        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_frame);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, new MenuListFragment())
                .commit();
        this.findViewById(R.id.iv_map_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenuWindow(mMapView);
            }
        });

        this.findViewById(R.id.iv_map_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchWindow(mMapView);

            }
        });

        // 开启定位图层
        BaiduMap baiduMap = mMapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        baiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            Overlay ol = null;

            @Override
            public void onMapLongClick(LatLng latLng) {
                if (ol == null) {
                    BaiduMap baiduMap = mMapView.getMap();
                    baiduMap.clear();
                    ol = baiduMap.addOverlay(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding)));
                    baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
                    mMapView.setTag(latLng);
                    showMenuWindow(mMapView);
                } else {
                    ol.remove();
                    ol = null;
                }

            }
        });
        baiduMap.setOnMapDoubleClickListener(new BaiduMap.OnMapDoubleClickListener() {
            @Override
            public void onMapDoubleClick(LatLng latLng) {
                hidePopupWindows();
            }
        });
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        mLocationClient.setLocOption(initLocOption());

        mLocationClient.start();


    }

    private LocationClientOption initLocOption() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置定位模式
        option.setCoorType("bd09ll");//返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(50000);//设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);//返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true);//返回的定位结果包含手机机头的方向
        return null;
    }


    private void showSearchWindow(View view) {
        if (mPopupSearchWindow == null)
            initSearchWindow(view.getContext());
        if (!mPopupSearchWindow.isShowing()) {
//            mPopupSearchWindow.showAsDropDown(view, -25, 10);
            mPopupSearchWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        } else {
            mPopupSearchWindow.dismiss();
        }
    }

    private void initSearchWindow(Context ctx) {
        DisplayMetrics matrix = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(matrix);
        //view窗体的显示控件
        View contentView = LayoutInflater.from(ctx).inflate(R.layout.map_popsearch, null);
        mPopupSearchWindow = new PopupWindow(contentView, matrix.widthPixels, 500);
//              mPopupSearchWindow.setBackgroundDrawable(new BitmapDrawable());//必须设置background才能消失
        mPopupSearchWindow.setBackgroundDrawable(new BitmapDrawable());
        //mPopupSearchWindow.setOutsideTouchable(false);

        //自定义动画
        //mPopupSearchWindow.setAnimationStyle(R.style.PopupAnimation);
        //使用系统动画
        //mPopupSearchWindow.setAnimationStyle(android.R.style.Animation_Translucent);
        mPopupSearchWindow.setOutsideTouchable(true);
        mPopupSearchWindow.setFocusable(true);
        mPopupSearchWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_FROM_FOCUSABLE);
        mPopupSearchWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupSearchWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        final EditText editText = (EditText) contentView.findViewById(R.id.et_search_loc);

        contentView.findViewById(R.id.iv_map_menu_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc = editText.getText().toString();
                if (loc == null || loc.length() == 0) {
                    editText.startAnimation(AnimationUtils.loadAnimation(MapActivity.this, R.anim.shake));
                    return;
                }
                String[] mapMenus = MapActivity.this.getResources().getStringArray(R.array.map_menu);
                for (String menu : mapMenus) {
                    Pattern p = Pattern.compile("^" + loc + "+");//正则表达式选取以你填的单词为首的所有查询结果
                    Matcher match = p.matcher(menu);
                    if (match.find()) {
                        markToMap(menu);
                        onBackPressed();
                        return;
                    }
                }
                editText.startAnimation(AnimationUtils.loadAnimation(MapActivity.this, R.anim.shake));
            }
        });

    }


    private Loc getLoc(String name) {
        XmlPullParser xmlResourceParser = Xml.newPullParser();
        setInput(xmlResourceParser);

        int eventType = 0;
        Loc loc = null;
        boolean found = false;
        double latitude = 0;
        double longitude = 0;
        try {
            eventType = xmlResourceParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:
                        if (xmlResourceParser.getName().equals("loc")) {
                            String locName = xmlResourceParser.getAttributeValue(0);
                            if (locName.equals(name)) {
                                found = true;
                                loc = new Loc();
                                loc.name = locName;
                                loc.iconName = xmlResourceParser.getAttributeValue(1);
                                loc.LatLons = new HashSet<LatLon>();
                            }
                            break;
                        }

                        if (found && xmlResourceParser.getName().equals("lat")) {
                            eventType = xmlResourceParser.next();
                            String lat = xmlResourceParser.getText();
                            latitude = Double.parseDouble(lat);
                        } else if (found && xmlResourceParser.getName().equals("lon")) {
                            eventType = xmlResourceParser.next();
                            String lon = xmlResourceParser.getText();
                            longitude = Double.parseDouble(lon);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (xmlResourceParser.getName().equals("gps")) {
                            if (loc != null) {
                                loc.LatLons.add(new LatLon(latitude, longitude));
                            }
                            latitude = 0;
                            longitude = 0;
                        }

                        if (xmlResourceParser.getName().equals("loc")) {
                            if (loc != null) return loc;
                        }
                        break;

                }
                eventType = xmlResourceParser.next();
            }

        } catch (XmlPullParserException e) {
            Log.w(TAG, e);
        } catch (IOException e) {
            Log.w(TAG, e);
        }
        return loc;
    }

    private void setInput(XmlPullParser xmlResourceParser) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            File xmlPath = new File(sdcardDir.getPath() + File.separator + OLV + File.separator + LOCS_XML);
            if (xmlPath.exists()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(xmlPath);
                    xmlResourceParser.setInput(fis, "UTF-8");

                } catch (XmlPullParserException e) {
                    Log.e(TAG, e.getMessage());
                    setXmlInput(xmlResourceParser);
                } catch (FileNotFoundException e) {
                    Log.e(TAG, e.getMessage());
                    setXmlInput(xmlResourceParser);
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                }

            } else {
                setXmlInput(xmlResourceParser);
            }

        } else {
            setXmlInput(xmlResourceParser);
        }
    }

    private void setXmlInput(XmlPullParser xmlResourceParser) {
        try {
            xmlResourceParser.setInput(this.getResources().openRawResource(R.raw.locs), "UTF-8");
        } catch (XmlPullParserException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    class Loc {
        String iconName;
        String name;
        Set<LatLon> LatLons;

        @Override
        public String toString() {
            return name + "=" + LatLons.size();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mLocationClient != null && mLocationClient.isStarted())
            mLocationClient.requestLocation();
        else
            Log.d("LocSDK4", "locClient is null or not started");


        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            File xmlPath = new File(sdcardDir.getAbsolutePath() + File.separator + OLV);
            if (!xmlPath.exists() && xmlPath.mkdirs()) {
                File locsFile = new File(xmlPath.getAbsolutePath() + File.separator + LOCS_XML);
                FileOutputStream fos = null;
                InputStream is = null;
                try {
                    fos = new FileOutputStream(locsFile);
                    is = this.getResources().openRawResource(R.raw.locs);
                    byte[] buf = new byte[1024];
                    int l = 0;
                    while ((l = is.read(buf)) > 0) {
                        fos.write(buf, 0, l);
                    }

                } catch (FileNotFoundException e) {
                    Log.e(TAG, e.getMessage());
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            Log.e(TAG, e.getMessage());
                        }

                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                }

            }

        }

    }

    private void markToMap(final String name) {
        if (mMapView != null) {
            if (mMapView.getTag() != null) {//cache loc LatLon
                LatLon LatLon = new LatLon((LatLng) mMapView.getTag());
                if (!addLocMap.containsKey(name)) {
                    addLocMap.put(name, new HashSet<LatLon>());
                }
                addLocMap.get(name).add(LatLon);
                mMapView.setTag(null);
                if (DEBUG)
                    Log.e(TAG, "ADD:" + name + File.separator + LatLon.toString());
                serializeLocsXml();
            }
            final Loc loc = getLoc(name);
            if (loc == null) {
                Log.e(TAG, "not found " + name);
                return;
            }
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(new ResourceUtil(this).getResId("map_" + loc.iconName, "drawable"));//构建Marker图标
            BaiduMap baiduMap = mMapView.getMap();
            baiduMap.clear();
            for (LatLon point : loc.LatLons) {
                if (point == null) continue;

                LatLng latLng = point.latLng();
                //定义Maker坐标点RRRRRRRR
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(latLng)
                        .icon(bitmap).title(loc.name);
                //在地图上添加Marker，并显示
                baiduMap.addOverlay(option);
                baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.getTitle().equals(loc.name)) {
                            if (!removeLocMap.containsKey(loc.name)) {
                                removeLocMap.put(loc.name, new HashSet<LatLon>());
                            }
                            removeLocMap.get(loc.name).add(new LatLon(marker.getPosition()));
                            if (DEBUG)
                                Log.e(TAG, "REMOVE:" + loc.name + File.separator + marker.getPosition().toString());
                            serializeLocsXml();
                            markToMap(loc.name);
                        }


                        return false;
                    }
                });
                if (DEBUG)
                    Log.e(TAG, loc.toString() + File.separator + latLng.toString());
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));

            }
        }
    }

    /**
     * 弹出框方法
     *
     * @param ctx
     */
    private void initMenuWindow(Context ctx) {
        DisplayMetrics matrix = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(matrix);
        //view窗体的显示控件
        View contentView = LayoutInflater.from(ctx).inflate(R.layout.map_popmenu, null);
        mPopupMenuWindow = new PopupWindow(contentView, matrix.widthPixels, 700);
//        mPopupMenuWindow.setBackgroundDrawable(new BitmapDrawable());//必须设置background才能消失
//        mPopupMenuWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.));
        //mPopupMenuWindow.setOutsideTouchable(false);

        //自定义动画
        //mPopupMenuWindow.setAnimationStyle(R.style.PopupAnimation);
        //使用系统动画
        //mPopupMenuWindow.setAnimationStyle(android.R.style.Animation_Translucent);

        mPopupMenuWindow.setOutsideTouchable(true);
//        mPopupMenuWindow.setTouchable(true);

        contentView.findViewById(R.id.tv_office_building).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("办公楼");
            }
        });
        contentView.findViewById(R.id.tv_spot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("景点");
            }
        });
        contentView.findViewById(R.id.tv_campus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("校区");
            }
        });
        contentView.findViewById(R.id.tv_teaching_building).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("教学楼");
            }
        });

        contentView.findViewById(R.id.tv_institution).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("科研机构");
            }
        });
        contentView.findViewById(R.id.tv_dormitory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("宿舍");
            }
        });

        contentView.findViewById(R.id.tv_dining).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("美食餐饮");
            }
        });

        contentView.findViewById(R.id.tv_shopping).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("商业购物");
            }
        });
        contentView.findViewById(R.id.tv_bank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("银行");
            }
        });

        contentView.findViewById(R.id.tv_hospital_pharmacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("医疗药店");
            }
        });

        contentView.findViewById(R.id.tv_entertainment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("娱乐场所");
            }
        });

        contentView.findViewById(R.id.tv_bus_station).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("公交站");
            }
        });

        contentView.findViewById(R.id.tv_ticket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markToMap("票务");
            }
        });


    }

    private void showMenuWindow(View view) {
        if (mPopupMenuWindow == null)
            initMenuWindow(view.getContext());
        if (!mPopupMenuWindow.isShowing()) {
            mPopupMenuWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    protected void onDestroy() {
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        if (mMapView != null) {
            mMapView.getMap().setMyLocationEnabled(false);
            mMapView.onDestroy();
            mMapView = null;
        }
        super.onDestroy();

    }

    private void serializeLocsXml() {
        if (addLocMap.size() > 0 || removeLocMap.size() > 0) {
            List<Loc> locList = new ArrayList<Loc>();
            String[] mapMenus = MapActivity.this.getResources().getStringArray(R.array.map_menu);
            for (String menu : mapMenus) {
                Loc loc = getLoc(menu);
                if (loc == null) continue;
                if (loc.LatLons == null) {
                    loc.LatLons = new HashSet<LatLon>();
                }
                if (addLocMap.containsKey(menu)) {
                    loc.LatLons.addAll(addLocMap.get(menu));
                }
                if (removeLocMap.containsKey(menu)) {
                    loc.LatLons.removeAll(removeLocMap.get(menu));
                }

                locList.add(loc);
            }

            XmlSerializer xml = Xml.newSerializer();
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File sdcardDir = Environment.getExternalStorageDirectory();
                File xmlPath = new File(sdcardDir.getPath() + File.separator + OLV);
                if (!xmlPath.exists()) xmlPath.mkdirs();
                File locsFile = new File(xmlPath.getAbsolutePath() + File.separator + LOCS_XML);
                if (DEBUG)
                    Log.e(TAG, "XML PATH:" + locsFile.getAbsolutePath());
                FileWriter fw = null;
                try {
                    fw = new FileWriter(locsFile);
                    xml.setOutput(fw);
                    xml.startDocument("UTF-8", true);
                    xml.startTag("", "root");
                    for (Loc loc : locList) {
                        if (loc == null) continue;
                        xml.startTag("", "loc");
                        xml.attribute("", "name", loc.name);
                        xml.attribute("", "icon_id", loc.iconName);
                        if (loc.LatLons == null || loc.LatLons.size() == 0) {
                            xml.endTag("", "loc");
                            continue;
                        }
                        for (LatLon ll : loc.LatLons) {
                            xml.startTag("", "gps");
                            xml.startTag("", "lat");
                            xml.text(ll.latitude + "");
                            xml.endTag("", "lat");
                            xml.startTag("", "lon");
                            xml.text(ll.longitude + "");
                            xml.endTag("", "lon");
                            xml.endTag("", "gps");
                            if (DEBUG)
                                Log.e(TAG, "serialize:" + loc.toString() + File.separator + ll.toString());
                        }
                        xml.endTag("", "loc");
                    }
                    xml.endTag("", "root");
                    xml.endDocument();

                } catch (FileNotFoundException e) {
                    Log.e(TAG, e.getMessage());
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                } finally {
                    if (fw != null) {
                        try {
                            fw.flush();
                            fw.close();
                        } catch (IOException e) {
                            Log.e(TAG, e.getMessage());
                        }

                    }
                }
            }
        }
        addLocMap.clear();
        removeLocMap.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        if (mMapView != null)
            mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        if (mMapView != null)
            mMapView.onPause();
    }

    @Override
    public void onBackPressed() {
        if (hidePopupWindows()) return;
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    private boolean hidePopupWindows() {
        if (mPopupMenuWindow != null && mPopupMenuWindow.isShowing()) {
            mPopupMenuWindow.dismiss();
            return true;
        }
        if (mPopupSearchWindow != null) {
            if (mPopupSearchWindow.isShowing()) {
                mPopupSearchWindow.dismiss();
                return true;
            }
        }
        return false;
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            if (location == null)
                return;
//            StringBuffer sb = new StringBuffer(256);
//            sb.append("time : ");
//            sb.append(location.getTime());
//            sb.append("\nerror code : ");
//            sb.append(location.getLocType());
//            sb.append("\nlatitude : ");
//            sb.append(location.getLatitude());
//            sb.append("\nlontitude : ");
//            sb.append(location.getLongitude());
//            sb.append("\nradius : ");
//            sb.append(location.getRadius());
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {
//                sb.append("\nspeed : ");
//                sb.append(location.getSpeed());
//                sb.append("\nsatellite : ");
//                sb.append(location.getSatelliteNumber());
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//            }
//            Log.e(TAG, sb.toString());

            if (location == null || mMapView == null) return;
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
            final BaiduMap baiduMap = mMapView.getMap();
            baiduMap.setMyLocationData(locData);

//        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//            MyLocationConfigeration config = new MyLocationConfigeration(MyLocationConfigeration.LocationMode.NORMAL, true, BitmapDescriptorFactory
//                    .fromResource(R.drawable.icon_gcoding));
//            mMapView.getMap().setMyLocationConfigeration(config);

            final LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(u);

            baiduMap.setOnMyLocationClickListener(new BaiduMap.OnMyLocationClickListener() {
                Overlay ol = null;

                @Override
                public boolean onMyLocationClick() {
                    if (DEBUG)
                        Log.e(TAG, "LOC:" + ll.toString());
                    if (ol == null) {
                        baiduMap.clear();
                        ol = baiduMap.addOverlay(new MarkerOptions().position(ll).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding)));
                        baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(ll));
                        mMapView.setTag(ll);
                        showMenuWindow(mMapView);
                    } else {
                        mMapView.setTag(null);
                        ol.remove();
                        ol = null;
                        onBackPressed();
                    }

                    return false;
                }
            });
//            if(mLocationClient!=null) mLocationClient.stop();
//            // 当不需要定位图层时关闭定位图层
//            baiduMap.setMyLocationEnabled(false);
        }
    }
}