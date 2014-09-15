package com.datang.olv.propaganda.overview;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.datang.olv.propaganda.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public final class LeaderFragment extends Fragment {
    // 声明ListView控件
    private ListView mListView;

    // 声明数组链表，其装载的类型是ListItem(封装了一个Drawable和一个String的类)
    private ArrayList<LeaderListItem> mList;
    private Context mctx;
    private LeaderListAdapter mAdapter;

    private View mview;

    private LayoutInflater mflater;

    private static String yangqiqing  = "杨奇庆，男，1947年10 月生，回族，江苏南京人，在职大专毕业，1966年5月入党，1968年8月参加工作。现任上海新侨职业技术学院理事长。";
    private static String zhoujian  = "周箴，男，1948年8月生，安徽芜湖人，博士，中共党员。曾任中共上海市委统战部副部长、市委第五巡视组组长，兼任同济大学经济与管理学院教授、博士生导师。学科专业是公共管理，具体研究方向是人力资源的管理和开发。现任上海新侨职业技术学院院长、党委副书记。";
    private static String chentingyu  = "1948年7月出生，教授，硕士生导师。1983年获工学（机械设计与理论）研究生硕士学位，1985年至1986年由教育部公派前苏联基辅工学院进修访问。曾任上海理工大学教务处副处长、上理工高职学院院长及继续教育学院院长，现任上海新侨职业技术学院副院长。";
    private static String zhulili  = "朱莉莉，女，1948年8月出生，浙江镇海人，中共党员，思政专业本科毕业，正处级，副教授，现任上海新侨职业技术学院副院长。";
    private static String wujianrong  = "吴建蓉，1955年3月生，上海市人，大学本科，中共党员，副教授。现任上海新侨职业技术学院副院长，上海市高职高专汽车类专业建设指导委员会副主任。";

    private static String yangqiqingdetail = " 杨奇庆，男，1947年10 月生，回族，江苏南京人，在职大专毕业，1966年5月入党，1968年8月参加工作。现任上海新侨职业技术学院理事长。\n" +
            "      杨奇庆同志1968年8月—79年3月，黑龙江生产建设兵团战士，历任宣传干事、连指导员、营副政治教导员、副场长。1979年3月—82年12月，上海黄浦区四川南路街道集体事业生产组工人、副组长，任党总支副书记。1982年12月—84年4月，任黄浦区四川南路街道党委委员（1980年9月—83年7月，在闸北区业余大学企业管理专业学习）。1985年5月—86年7月，任黄浦区集体事业管理局党委副书记。1985年5月—86年7月，任黄浦区区长助理。1986年7月—87年4月，任黄浦区区长助理、区计划经济委员会主任。1987年4月—87年8月，任黄浦区计划经济委员会主任。1987年8月—90年2月，任黄浦区人事局局长。1990年2月—93年2月，任中共黄浦区委常委、组织部长。1993年2月—97年7月，任中共黄浦区委副书记。1997年7月—98年3月，任市民族事务委员会副主任、党组书记。1998年3月—2000年4月，任市民族事务委员会主任、党组书记。2000年4月—2001年9月任市民族和宗教事务委员会党委副书记、主任。2001年9月—2003年12月，任市委统战部副部长、市民族和宗教事务委员会党委书记、主任。2004年1月—2008年1月，任十届市政协秘书长、党组成员。2008年1月—2011年1月，任十一届市政协常委、市政协民族宗教委员会主任。\n" +
            "      杨奇庆同志是第九、十届全国人大代表。是市第六、七、八、九届党代会代表。";

    private static String zhoujiandetail = "周箴，男，1948年8月生，安徽芜湖人，博士，中共党员。曾任中共上海市委统战部副部长、市委第五巡视组组长，兼任同济大学经济与管理学院教授、博士生导师。学科专业是公共管理，具体研究方向是人力资源的管理和开发。现任上海新侨职业技术学院院长、党委副书记。\n" +
            "    1978年国家恢复高考制度，在同济大学物理系学习， 获理学学士学位。1978年7月毕业留校工作，1997年至2003年，在职攻读研究生，并获得管理学博士学位。\n" +
            "    在同济大学工作期间，历任物理系教师、党总支副书记、书记、系副主任，经济与管理学院院长，校党委副书记、常务副校长等职务。期间，1990年7月至1992年9月，由国家教委派往中国驻美国使馆教育处任二等秘书，负责美国高校的联系及中国留学生事务。\n" +
            "    2000年10月至2008年10月，担任中共上海市委统战部副部长、上海市海外联谊会副会长、上海市人大代表或上海市政协委员、上海《现代领导》理事会副会长、上海安徽经济、文化、教育促进会副会长等职。2008年底至2012年2月，担任中共上海市委第五巡视组组长、上海市政协委员、教科文卫体专委会常务副主任、上海海外联谊会副会长、上海《现代领导》理事会副会长、上海东方华文教育发展中心理事长等职。\n" +
            "    专业领域主要从事公共管理学科人力资源管理与开发方向的教学和科研工作，并承担该学科领域的硕士、博士导师。为企业、公共管理方向的本科生、硕士生及博士生讲授公共管理及人力资源管理和开发等课程，主持和参与过深圳三九集团、河南邦杰（集团）、上海汽车销售总公司、上海采尔浮转向轴有限公司等单位人力资源管理和开发和上海市社科院、中央统战部等单位公共管理及社会科学方面的研究课题。自大学毕业以来曾发表论文二十余篇，出版专业书《在华跨国公司人力资源管理与开发》一本。获专利二项，获国家教委科技进步二等奖、第七届国家级二等企业管理现代化创新奖、上海社科院、全国统战理论研究优秀成果二等奖或三等奖等近十项奖项。";

    private static String chentingyudetail = "1948年7月出生，教授，硕士生导师。1983年获工学（机械设计与理论）研究生硕士学位，1985年至1986年由教育部公派前苏联基辅工学院进修访问。曾任上海理工大学教务处副处长、上理工高职学院院长及继续教育学院院长，现任上海新侨职业技术学院副院长。\n" +
            "   　在校工作期间，曾获2001年—2008年间获上海市教学成果二等奖、上海市教学成果一等奖、国家教学成果二等奖（第三作者），上海市育才奖等奖项。\n" +
            "   　曾参编《现代机械设备设计手册》（全国科技图书一等奖，机械工业出版社），参编《现代机械传动设计手册》（机械工业出版社），参编《机械原理》（本科规划教材，机械工业出版社），主审《机械设计基础》（高职规划教材，机械工业出版社），在核心刊物上发表专业学术论文20多篇，EI 收录2篇。";

    private static String zhulilidetail = "朱莉莉，女，1948年8月出生，浙江镇海人，中共党员，思政专业本科毕业，正处级，副教授，现任上海新侨职业技术学院副院长。\n" +
            "　　复兴中学66届高中毕业，文革中上山下乡。71年上调至上海师范学院政教系学习，后分配到上海市北郊中学任教，曾担任高中政治教师，校团委书记、教导处副主任、校党支部宣传委员等职。\n" +
            "　　1984年8月被调至虹口区教育局中教科工作， 分管全区中学生思政教育。\n" +
            "　　1984年10月被调至上海市人民政府教育卫生办公室教育处工作，负责联系上海市教育局。\n" +
            "　　1987年3月调至上海外国语大学工作，曾任西语系党总支书记、系副主任；西方语学院党总支书记，副院长，校党委纪委委员。期间，西语系党总支被评为上海市教卫系统先进党组织；西方语学院被评为上海市“学理论、学党章”先进党组织。其本人荣获上海市三八红旗手，上海市教卫系统综合治理先进个人等称号。\n" +
            "　　2004年9月被中侨职业技术学院聘为副院长、副书记。在中侨工作的五年中，在开设多个小语种，组建外语学院，成教学院，开拓国际交流，文明创建，工青妇等方面作出显著成绩，带领中侨学院连续两届荣获教委级文明单位，并多次代表学院在民办党工委大会作交流发言。2008年7月，荣获民办党工委系统“优秀党务工作者”称号。\n" +
            "　　2009年4月被上海市民办高教协会和民办党工委任命为民办高校就业协作组主任，2010年起兼任协会副秘书长。两年来，带领21所民办高校就业办主任，团结协作，克服金融危机带来的重重困难，使民办高校就业率稳步上升，2010年达到了96%以上，以高于公办高校的成绩，获得市教委和民办党工委的好评。\n" +
            "　　2009年9月至2011年8月调至上海杉达学院工作，任党委负责人，分管全校学生工作。09 年为杉达成功开设西语专业，使之成为上海第三所设有西语本科的高校。两年来在加强大学生思想政治工作和辅导员队伍建设，推进毕业生就业和心理健康教育，规范团学两级组织和丰富校园文化生活等方面，进行了卓有成效的工作，得到师生好评。";

    private static String wujianrongdetail = "吴建蓉，1955年3月生，上海市人，大学本科，中共党员，副教授。现任上海新侨职业技术学院副院长，上海市高职高专汽车类专业建设指导委员会副主任。\n" +
            "　　曾任上海市机械工业学校机电专业主任、上海新侨职业技术学院汽车与数控系主任、院长助理、主持工作副校长等职。上海市教委机械类专业兼职教研员、上海市中职校机械专业组组长。曾负责主持上海市中等职业学校机械制造技术专业教学标准制定。参加教育部组织的中等职业学校机械基础教学大纲制定。\n" +
            "　　曾公开发表的论文有《特殊结构齿轮的加工》、《弧齿锥齿轮的过载损坏及防护措施》等多篇。主编教材《工程力学与机械设计基础》、《数控加工技术与应用》、《机械基础》。 教材《工程力学与机械设计基础》获上海市普通高校优秀教材二等奖。参与研究的课题 “准确定位，把握特点——高职人才培养的改革创新”获上海市教学成果二等奖。";

    private ArrayList<LeaderDetailItem> mLeaderDetail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mLeaderDetail = new ArrayList<LeaderDetailItem>();

        LeaderDetailItem additem = new LeaderDetailItem();
        additem.setTextDetail(yangqiqingdetail);
        additem.setImageID(R.drawable.yangqiqing);
        mLeaderDetail.add(additem);

        additem = new LeaderDetailItem();
        additem.setTextDetail(zhoujiandetail);
        additem.setImageID(R.drawable.zhoujiandetail);
        mLeaderDetail.add(additem);

        additem = new LeaderDetailItem();
        additem.setTextDetail(chentingyudetail);
        additem.setImageID(R.drawable.chengtingyudetail);
        mLeaderDetail.add(additem);

        additem = new LeaderDetailItem();
        additem.setTextDetail(zhulilidetail);
        additem.setImageID(R.drawable.zhulilidetail);
        mLeaderDetail.add(additem);

        additem = new LeaderDetailItem();
        additem.setTextDetail(wujianrongdetail);
        additem.setImageID(R.drawable.wujianrongdetail);
        mLeaderDetail.add(additem);

        mflater = inflater;

        mview = (View)inflater.inflate(R.layout.fragment_leader, null);

        mListView = (ListView)mview.findViewById(R.id.leader_list);

        // 获取Resources对象
        Resources res = this.getResources();

        mctx = this.getActivity();

        mList = new ArrayList<LeaderListItem>();

        LeaderListItem item = new LeaderListItem();
        item.setImage(res.getDrawable(R.drawable.yangqiqing));
        item.setTitle(yangqiqing);
        mList.add(item);

        item = new LeaderListItem();
        item.setImage(res.getDrawable(R.drawable.zhoujian));
        item.setTitle(zhoujian);
        mList.add(item);

        item = new LeaderListItem();
        item.setImage(res.getDrawable(R.drawable.chentingyu));
        item.setTitle(chentingyu);
        mList.add(item);

        item = new LeaderListItem();
        item.setImage(res.getDrawable(R.drawable.zhulili));
        item.setTitle(zhulili);
        mList.add(item);

        item = new LeaderListItem();
        item.setImage(res.getDrawable(R.drawable.wujianrong));
        item.setTitle(wujianrong);
        mList.add(item);

        mAdapter = new LeaderListAdapter(mList, mctx, this);

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LeaderDetailItem detailinfo = mLeaderDetail.get(i);
                Intent intent = new Intent(mview.getContext(),
                        LeaderDetailActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",detailinfo.getImageID());
                value.putString("text",detailinfo.getTextDetail());

                intent.putExtra("DATA",value);

                mview.getContext().startActivity(intent);
            }
        });

        return mview;
    }

    /**
     * 封装了两个资源的类
     */
    public class LeaderListItem {
        private Drawable image;
        private String title;

        public Drawable getImage() {
            return image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }

    public class LeaderDetailItem{
        private String TextDetail;
        private int ImageID;

        public String getTextDetail() {
            return TextDetail;
        }

        public void setTextDetail(String textDetail) {
            TextDetail = textDetail;
        }

        public int getImageID() {
            return ImageID;
        }

        public void setImageID(int imageID) {
            ImageID = imageID;
        }
    }
}
