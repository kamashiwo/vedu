package com.datang.olv.propaganda.majors;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.datang.olv.propaganda.overview.CollegeFragment;
import com.datang.olv.propaganda.overview.HistoryFragment;
import com.datang.olv.propaganda.overview.LeaderDetailActivity;
import com.datang.olv.propaganda.overview.LeaderFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 14-7-21.
 */
public class MajorsActivity extends FragmentActivity {
    SlidingMenu menu;

    // 声明ListView控件
    private ListView mListView;

    // 声明数组链表，其装载的类型是ListItem(封装了一个Drawable和一个String的类)
    private ArrayList<MajorsItem> mList;

    private ArrayList<MajorsDetailItem> mDetailItem;

    private MajorsListItemAdapter mAdapter;

    private Context mctx;

    private static String jidiangongchengxiZhu  = "机电工程系";
    private static String jidiangongchengxiFu  = "机电工程系着重培养以机械电子技术为基础，从事现代制造装备业急需的生产第一线的高素质，技能型，应用型人才";

    private static String jisuanjixinxiZhu  = "计算机信息系";
    private static String jisuanjixinxiFu  = "计算机信息系创建于1999年，现共有计算机应用技术，计算机网络技术，计算机信息原理，应用电子技术和通信技术五个专业";

    private static String jingjimaoyiyuguanliZhu  = "经济贸易与管理系";
    private static String jingjimaoyiyuguanliFu  = "经济贸易与管理系与上海新侨职业技术学院一起创建于1999年，现共有物流管理，国际商务，电子商务3个专业";

    private static String qichegongchengxiZhu  = "汽车工程系";
    private static String qichegongchengxiFu  = "我院汽车工程系目前设有汽车运用技术专业，汽车电子技术专业，汽车技术服务与营销专业和汽车定损与评估专业";

    private static String yingyongwaiyuxiZhu  = "应用外语系";
    private static String yingyongwaiyuxiFu  = "应用外语系目前设有3个专业，应用英语，应用日语，涉外事务管理。";

    private static String zhubaoyishuyushejixiZhu  = "珠宝艺术与设计系";
    private static String zhubaoyishuyushejixiFu  = "上海新侨职业技术学院珠宝与艺术系创建于1999年，下设珠宝首饰工艺与鉴定专业和应用艺术专业，其中珠宝首饰工艺与鉴定专业是本市高校中创办最早的特色专业";

    private static String jichubuZhu  = "基础部";
    private static String jichubuFu  = "基础部承担了全院主要公共基础课程的角教学任务，内设思改教研室，公共基础教研室。";

    private static String jixujiaoyuxueyuanZhu  = "继续教育学院";
    private static String jixujiaoyuxueyuanFu  = "基础部承担了全院主要公共基础课程的角教学任务，内设思改教研室，公共基础教研室。";


    private static String jidiangongchengxidetail  = "机电工程系创建于1999年，经过几年的迅速发展，无论从学生人数，专业数量，师资队伍，校内、外实训基地等方面都跃上了新的台阶。伴随着经济全球化，我国制造业成为国民经济的核心，而机电专业成为造业发展的“发动机”。现在整个嘉定区有企业三千余家，需要机电人才的企业占一半以上。\n" +
            "    机电工程系是我院重点建设、最具实力和特色的系部之一，设有机电一体化技术和数控技术两个专业。现有专任教师23名，其中正高级职称2人，副高级职称5人，中级职称5人，高级技师1人，技师3人，高级工3人。博士1人，硕士10人。双师型教师13人。机电一体化教学团队为上海市级教学团队，在各类比赛中屡屡获奖。      \n" +
            "    机电工程系紧紧围绕高职高专特色办学，根据学生的特点，因材施教，着重培养学生的实践技能。在政府专项资金扶持下，近年来投入资金600万元，加强对硬件设施、教学设备、仪器的建设。现有金工实习工厂、数控实训室、维修电工实训室、数控仿真实训室、CAD/CAM实训室、机械基础实验室和机电一体化实训室、检测实训室等，总面积1500m2以上，校内实训基地设备先进、功能较齐、工位数较充足。机电工程系无论是办学条件、教学设施等硬件条件及师资队伍建设方面，都在上海市同类院校中具备占有一定优势。先进的实验室、实训室，为提高学生动手能力，体现高职特色提供了有力保障。\n" +
            "    本系坚持学院“诚信办学，质量立校，人才兴校，特色强校”的办学理念， 坚持让学生成为适应工作变化的知识型、发展型技能人才，坚持育人为本，深化订单式培养，产学交融等多样化的人才模式改革。机电工程系正在与上海大众汽车公司进行深度校企合作，定于2014年9月招收“新侨大众班”学生，并与英国博尔顿大学进行国际交流合作。\n" +
            "    机电工程系一直坚持以学生职业生涯规划过程化、不间断发展为导向。我们的培养目标明确，培养模式科学，培养体系完善，培养质量保证。";

    private static String jisuanjixinxidetail  = "　　计算机信息系创建于1999年，现共有计算机应用技术、计算机网络技术、计算机信息管理、应用电子技术和通信技术五个专业。\n" +
            "　　计算机信息系至创建以来，已有毕业生近2000名左右，获得“双证”（指的是毕业证和高级职业技能证）比例较高，历年的就业率高 ，据统计：毕业生平均就业率为98%左右，称职率在96%以上，特别是2011-2012年度的毕业生，就业率达到100%。\n" +
            "本系专任教师共有19名，其中有博士1名、硕士12名；中高级职称占74%，具有一支学历较高、技能较强、实战经验较为丰富的优秀专兼职师资队伍。\n" +
            "　　教师教学科研成果显著。有的教师成为“2005年上海市普教系统名师培养工程”后备人选； 有的教师荣获2007年上海市育才奖；有的在全国公开发行软件产品3套共5个产品 ；有的承担上海高校选拔培养优秀青年教师科研专项基金等6项科研项目；有的项目荣获上海市青年教师教育学研究三等奖。公开发表论文数十篇之多，2013年获得上海市教育委员会民办高校科研项目。\n" +
            "　　2011年度《Linux基础与应用》和2013年度《Java Web应用软件开发》分别被由上海市教育委员会评为上海高职院校市级精品课程，有3门课程获批院级精品课程，有6门核心课程作为院级重点建设课程。\n" +
            "计算机应用技术（移动互联网应用软件开发）2012年是上海市“085工程”重点专业，2013年申报上海市特色专业。\n" +
            "　　通信技术（移动通信）专业与上海大唐移动通信设备有限公司校企合作共建3G-4G移动通信基站实训室，实施学校与大唐公司联合人才培养方案。\n" +
            "计算机网络技术专业与美国思科系统（中国）网络技术有限公司及北京欧朋兰博企业投资管理有限公司合作办学，实施联合人才培养方案。\n" +
            "　　2008年起，本系及时跟踪市场需求的变化，主动适应IT产业发展的需要，我系按照国家教育部的指示精神，开始建立校企合作、工学结合，突出实践能力的人才培养教学模式，学校与企业共同制定培养实施方案、共同确定课程体系、共同建设师资队伍、共同建设校内、校外实训基地、共同指导就业。";

    private static String jingjimaoyiyuguanlidetail  = "商务系与上海新侨职业技术学院一起创建于1999年，现共有物流管理、国际商务、电子商务3个专业。\n" +
            "    本系现有教职工25人，其中，高级职称5名，中级职称12名，双师型教师10名，兼职教师10名，均为高级职务或行业专家，是一支学历较高、技能较强、实战经验较为丰富的优秀专兼职师资队伍。\n" +
            "    本系教学科研成果显著。有2位教师承担了上海高校选拔培养“晨光”计划教师科研专项基金，还有5位教师承担了上海高校选拔培养的优秀青年教师科研专项基金。教师累计在核心期刊发表学术论文10多篇。\n" +
            "    管理系建于1999年，现共有会计、旅游管理、酒店管理、会展策划与社区服务5个专业。本系现有教职工18人，其中，高级职称5名，中级职称12名，双师型教师14名，兼职教师10名，均为高级职务或行业专家，是一支高学历、技能强、实战经验丰富的优秀专兼职师资队伍。本系教学科研成果显著。有2位教师承担了上海高校选拔培养“晨光”计划教师科研专项基金，还有5位教师承担了上海高校选拔培养的优秀青年教师科研专项基金。教师累计编著公开出版的《基础会计》、《高级会计》、《社会经济统计与原理》、《外贸会计实务教程》等教材10多本。在核心期刊发表学术论文20余篇。 被评为全国、华东地区、上海市优秀论文一等奖、二等奖共13篇。本系及时跟踪市场需求的变化，主动适应产业发展的需要，校企合作、工学结合、突出实践教学，由学校与企业共同制定培养实施方案、共同确定课程体系、共同建设师资队伍、共同建设校内、校外实训基地、共同指导就业，形成了“市场导向、岗位主导、校企融合”的人才培养模式。";

    private static String qichegongchengxidetail  = "    我院汽车工程系目前设有汽车运用技术专业、汽车电子技术专业、汽车技术服务与营销专业和汽车定损与评估专业。目前已有毕业生2049名，在校生462名。\n" +
            "　　我系教师共37人。专任教师占18人，其中高级职称6人，中级职称7人，中高级职称占72%；具有硕士学位9人，占50%；45岁以下青年教师11人，占61%；双师素质教师11人，占61%。\n" +
            "　　为了培养高等职业技能应用型人才，全面提升学生的职业素质，构建了实践教学与理论教学、实践教学与技能操作、实践教学与职业资格等级考证相结合的实践教学体系，实现了学生在毕业时能够取得一张毕业文凭和多张职业技能证书。\n" +
            "　　校内实验、实训场所设备齐全、工位充足，建有总面积为3200m2，总投入为1600万元的汽车实训基地，主要包括：汽车运用技术实训中心、汽车技术服务与营销实训中心、汽车定损与理赔实训中心、汽车电子技术实训中心、汽车仿真软件教学室、汽车模型与教具实验室、汽车液压与气动实验室、汽车电控技术实验室等，能充分满足汽车类各专业的校内实验、实训的需求。同时，拥有近30家校外实训基地，为学生走向工作岗位提供有力的保障。\n" +
            "　　其毕业生广泛就业于各种汽车4S特约维修站、特许加盟快修店、连锁维修店、检测站、定损理赔中心、经贸公司、物流公司、销售公司、汽车制造厂及其它相关企业的设备管理部门。基本上能达到市场对毕业生在知识、素质和综合能力的需求。在长三角地区已享有一定的知名度和较高的声誉并广受企业的好评。近三年毕业生的就业率为98%~100%。";

    private static String yingyongwaiyuxidetail  = "应用外语系目前设有3个专业，应用英语、应用日语、涉外事务管理。教师重视教学与科研，近年来发表论文50多篇，主编或参编著作、教材10多部，主持或参加各级科研课题8项，具有较高的科研水平，获得市级以上竞赛和优秀成果奖多项。该系学生积极参与国家级、市级技能竞赛，在比赛中均取得了较为优秀的成绩。\n" +
            "      応用外国語学科は現在     応用英語、応用日本語、国際事務管理という三つの専攻が設けられている。教員は教育方法の研究を重視するとともに、ここ数年発表した論文は５０余りも超え、１０冊以上の教材の編集にも参加した。\n" +
            "      The Applied Foreign Languages Department currently offers three specialties, namely, Applied English,    Applied Japanese and Foreign Affairs Management. The teachers in this department pay much attention to teaching as well as scientific research.";

    private static String zhubaoyishuyushejixidetail  = "上海新侨职业技术学院珠宝与艺术系创建于1999年，下设珠宝首饰工艺与鉴定专业和应用艺术专业。其中珠宝首饰工艺与鉴定专业是本市高校中创办最早的特色专业。\n" +
            "    本专业是为上海和周边大城市培养生产管理一线技术人才，其指导思想是：以行业需求为依据，以提高学生的职业能力和职业素养为宗旨，倡导以学生为本位的办学理念和建立多样性与选择性相统一的教学机制。通过综合和具体的职业实践活动，帮助学生积累实际工作经验，突出职业教育的特色，全面提高学生的职业道德、职业能力和综合素质。学制三年，大专高职学历。招生对象：高中和三校毕业生。\t";

    private static String jichubudetail  = "    基础部承担了全院主要公共基础课程的教学任务，内设思政教研室（毛泽东思想和中国特色社会主义理论体系概论、思想道 德修养与法律基础、大学生心理健康、大学生就业指导），公 共基础教研室（高等数学、体育、职业汉语）。 　　\n" +
            "    基础部拥有一支业务精良、师德高尚、教学严谨、高素质、高学历的教师队伍。现有专兼 职教师27人，其中正高级职称2名，副高级职称13名，讲师15名， 助教7名。全体教师致力于做好本职工作，加强公共基础课为专业课服务的理念，使基础课成为专业课的基石。同时教师们秉着授人以鱼且授人以渔的教学态度，使学生在掌握基础知识的前提下具备自学及研究专业知识的综合能力，全面提高教育教学质量。基础部本着一切为了学生，致力将我校学生培养成“专业知识有特点、技术能力有水平、职业生涯有规划、终身发展有潜力”的新侨四有人才。 　　\n" +
            "    教师教学科研成果显著。完成了国家教师科研基金“十一五”重点课题项目《高等教育系统的办学效益分析与评价》并获得二等奖；目前承担着上海市教育基金会“晨光计划”项目一项；上海市教委高校优秀青年教师科研课题四项。历年来公开发表在各级各类学术刊物论文20余篇。 主编了上海市高职高专教材《高等数学》等。思想政治理论课教师在上海市第四届民办高校思政课教学竞赛中获得三等奖。从2003年至今，每年组织学生参加全国大学生《数学建模》竞赛，年年获奖，在同类院校中名列前茅。";

    private static String jixujiaoyuxueyuandetail  = "继续教育学院,";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_majors);
        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MajorsActivity.this.finish();

            }
        });

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

        mDetailItem = new ArrayList<MajorsDetailItem>();

        MajorsDetailItem ditem = new MajorsDetailItem();
        ditem.setStrText(jidiangongchengxidetail);
        ditem.setImageID(R.drawable.jidiangongchengxidetail);
        mDetailItem.add(ditem);

        ditem = new MajorsDetailItem();
        ditem.setStrText(jisuanjixinxidetail);
        ditem.setImageID(R.drawable.jisuanjixidetail);
        mDetailItem.add(ditem);

        ditem = new MajorsDetailItem();
        ditem.setStrText(jingjimaoyiyuguanlidetail);
        ditem.setImageID(R.drawable.guanlidetail);
        mDetailItem.add(ditem);

        ditem = new MajorsDetailItem();
        ditem.setStrText(qichegongchengxidetail);
        ditem.setImageID(R.drawable.qichegongchengdetail);
        mDetailItem.add(ditem);

        ditem = new MajorsDetailItem();
        ditem.setStrText(yingyongwaiyuxidetail);
        ditem.setImageID(R.drawable.yingyongwaiyudetail);
        mDetailItem.add(ditem);

        ditem = new MajorsDetailItem();
        ditem.setStrText(zhubaoyishuyushejixidetail);
        ditem.setImageID(R.drawable.zhubaodetail);
        mDetailItem.add(ditem);

        ditem = new MajorsDetailItem();
        ditem.setStrText(jichubudetail);
        ditem.setImageID(R.drawable.jichubudetail);
        mDetailItem.add(ditem);

        ditem = new MajorsDetailItem();
        ditem.setStrText(jixujiaoyuxueyuandetail);
        ditem.setImageID(0);
        mDetailItem.add(ditem);

        // 获取Resources对象
        Resources res = this.getResources();

        mListView = (ListView)findViewById(R.id.majors_list);

        mctx = this;

        mList = new ArrayList<MajorsItem>();

        MajorsItem item = new MajorsItem();
        item.setZhuText(jidiangongchengxiZhu);
        item.setFuText(jidiangongchengxiFu);
        mList.add(item);

        item = new MajorsItem();
        item.setZhuText(jisuanjixinxiZhu);
        item.setFuText(jisuanjixinxiFu);
        mList.add(item);

        item = new MajorsItem();
        item.setZhuText(jingjimaoyiyuguanliZhu);
        item.setFuText(jingjimaoyiyuguanliFu);
        mList.add(item);

        item = new MajorsItem();
        item.setZhuText(qichegongchengxiZhu);
        item.setFuText(qichegongchengxiFu);
        mList.add(item);

        item = new MajorsItem();
        item.setZhuText(yingyongwaiyuxiZhu);
        item.setFuText(yingyongwaiyuxiFu);
        mList.add(item);

        item = new MajorsItem();
        item.setZhuText(zhubaoyishuyushejixiZhu);
        item.setFuText(zhubaoyishuyushejixiFu);
        mList.add(item);

        item = new MajorsItem();
        item.setZhuText(jichubuZhu);
        item.setFuText(jichubuFu);
        mList.add(item);

        item = new MajorsItem();
        item.setZhuText(jixujiaoyuxueyuanZhu);
        item.setFuText(jixujiaoyuxueyuanFu);
        mList.add(item);

        mAdapter = new MajorsListItemAdapter(mList, mctx, this);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MajorsDetailItem detailinfo = mDetailItem.get(i);
                Intent intent = new Intent(mctx,
                        MajorsDetailActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",detailinfo.getImageID());
                value.putString("text",detailinfo.getStrText());

                intent.putExtra("DATA",value);

                mctx.startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    public class MajorsItem {
        private String ZhuText;
        private String FuText;
        public String getZhuText() {
            return ZhuText;
        }
        public void setZhuText(String zhuText) {
            ZhuText = zhuText;
        }
        public String getFuText() {
            return FuText;
        }
        public void setFuText(String fuText) {
            FuText = fuText;
        }
    }

    public class MajorsDetailItem {
        private int imageID = 0;
        private String strText;

        public int getImageID() {
            return imageID;
        }

        public void setImageID(int imageID) {
            this.imageID = imageID;
        }

        public String getStrText() {
            return strText;
        }

        public void setStrText(String strText) {
            this.strText = strText;
        }
    }
}