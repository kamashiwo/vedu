package com.datang.olv.propaganda.institution;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.datang.olv.propaganda.majors.MajorsDetailActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

/**
 * Created by l on 14-7-21.
 */
public class InstitutionActivity extends FragmentActivity {
    SlidingMenu menu;

    // 声明ListView控件
    private ListView mListView;

    // 声明数组链表，其装载的类型是ListItem(封装了一个Drawable和一个String的类)
    private ArrayList<String> mList;

    private Context mctx;

    private ArrayList<String> mDetailList;

    private InstitutionListItemAdapter mAdapter;
    private static String jiaowuchu = "教务处";
    private static String xueshengchu = "学生处";
    private static String shixunchu = "实训处";
    private static String dudaokeyanchu = "督导科研处";
    private static String renshichu  = "人事处";
    private static String guojijiaoliuchu = "国际交流处";
    private static String houbaochu = "后保处";
    private static String xinxiban = "信息办";

    private static String jiaowuchudetail = " 教务处是在院部领导下，组织教学及实施教学管理并面向教学一线师生服务的综合职能部门，负责学校教学工作的组织、管理、协调和检查。具体职责如下：\n" +
            "    1.根据上级的方针、政策，结合我校实际情况，组织实施教学工作发展规划，组织拟订和实施全校教学工作年度计划。\n" +
            "    2.根据社会经济发展需求，调整专业设置，优化专业结构。\n" +
            "    3.负责组织各系制定和实施专业教学计划；制定各课程教学大纲及各种教学文件和教学管理的规章制度，并做好教学档案的管理工作。\n" +
            "    4.负责会同各系积极开展校企合作办学与管理工作，组织制定和实施各专业实习计划，组织、协调各系抓好学生实习、抓好毕业实习、毕业综合实训等实践教学管理工作，并组织各专业做好校内外实习基地的建设和管理工作。\n" +
            "    5.实施教学运行管理，负责全校的排课、考务及教材供应等。维护正常教学秩序，负责学校固定资产等教学资源的管理、协调。\n" +
            "    6.负责教学质量监控工作，监督检查各系、部教学计划执行情况，组织教学质量检查和评估。定期组织教学检查，撰写每学期教学状况分析报告，协调和解决教学工作中出现的各种问题。\n" +
            "    7.有计划组织各系开展专业建设、课程建设和教材建设，推进教学改革，提高教学质量。\n" +
            "    8.会同各系搞好兼职教师的聘任与管理工作，做好专任教师教学工作考核及学生评教工作、教师评学工作，负责教师教学工作量的统计和结算工作。\n" +
            "    9.负责全校学生的学籍管理、成绩管理、毕业资格审核工作，办理学生的毕业证书工作，办理学生学历和学习成绩证明等。\n" +
            "    10.负责教学文件、资料、讲义、试卷的印制和保管工作。\n" +
            "    11.完成院领导交办的其它工作。";

    private static String xueshengchudetail = "学生处";

    private static String shixunchudetail = "实习实训与设备管理处是学院下属的独立部门，部门主要负责全院内外实习实训安排与管理，学生职业技能培训与考证管理工作，校内外各类竞赛的规划与管理，学校与系部校企合作项目管理，学校设备资产及政府扶持资金的管理以及学校数据状态平台的管理，是学院教学与管理并重的主要职能部门。";

    private static String dudaokeyanchudetail = "督导工作主要行使三大功能和八字方针。三大功能为：检查功能、服务功能和导向功能——在调查研究的基础上，深入开展检查工作，并将\n" +
            "检查成果通过信息反馈和建议，服务于学校的总目标和中心工作；八字方针为：监督、检查、评估、咨询——督导工作要有助于学校工作的决策和操作，但本身不行使相应职权。\n" +
            "监督  学校贯彻教育方针政策，执行有关法律、法规及学校制定的各项规章制度以及落实各项工作任务的情况；开展素质教育、提高教学质量，推进教学改革的情况；倡导、培育优良校风和学风的情况。\n" +
            " 检查  学校管理、职能部门作出的有关教育、教学管理及发展的计划、构想；教育教学工作各个环节的具体内容；教师职业道德、业务水平、精神面貌和学生在德智体美发展方面的动态指标。\n" +
            " 评估  学校的培养目标和手段，教育教学工作的方向，课程设置的效果；相关“精品”专业的教学目标、组织管理机制、业绩、形象宣传等；“名师”工程(对忠诚党的教育事业、在教育教学工作中作出突出贡献的教师的经验进行总结、宣传，探索并完善学校工作的奖惩机制)的建设。\n" +
            " 咨询  根据学校中心工作的需要，针对教育、教学实践中出现的问题，向校有关领导提出有针对性、可操作性的意见和建议；向有关管理和职能部门提供咨询服务；有计划、有重点、分阶段地对学校的教育教学工作等提出建议，以有利于学生健全人格的塑造，不断提高学生思想认识和创新水平；帮助相关学科的领导和教师及时总结和调整教学理念、教学原则和方法。";

    private static String renshichudetail = "人事处";

    private static String guojijiaoliuchudetail = "国际交流处";

    private static String houbaochudetail = "后勤保卫处是学院适应高校后勤改革，贯彻以人为本的科学发展观，按照“精简高效”的原则，深入推进后勤服务保障体系建设而组建的后勤管理及安全保卫行政职能部门，在学院党委和行政的领导下，以“主动保障、人文管理、严谨工作、满意服务”为工作的指导思想、原则和目标，全面负责学院的后勤保障、基本建设、资产管理以及安全保卫工作。\n" +
            "主要职能是：\n" +
            "一、履行学院后勤工作的计划、管理、协调与监控职能，行使学院维修经费预算、后勤经费使用与控制、合同签约、工程质量监督、服务价格检查、维修项目验收等职权；\n" +
            "二、代表学院对外协调后勤与地方政府有关部门的关系，对内协调学院各职能部门及师生员工与后勤服务部门的关系；\n" +
            "三、负责学院下达的预算内各项后勤经费的使用和管理工作；\n" +
            "四、负责服务过程中的质量监督工作，收集师生员工的反馈意见；\n" +
            "五、负责学院物资设备管理、设备购置计划、使用效率检查及采购供应工作；\n" +
            "六、负责学院各类房产、地产、设施的管理等工作；\n" +
            "七、宣传、贯彻国家和学院关于社会治安综合治理工作的方针、政策，协调、督促、监督学院各系、各部门的社会治安综合治理工作，组织落实社会治安综合治理及消防安全工作责任制；\n" +
            "八、在上级公安机关的业务指导下，建立健全与落实学院社会治安综合治理各项措施，指导、督促开展安全检查，开展调查研究，预防刑事犯罪、治安案件、火灾等灾害事故的发生。确保校园治安秩序，保护师生员工人身与公私财物安全，为教学、科研和生活创造良好的环境，为学院的稳定、改革和发展提供各种安全保障服务。\n" +
            "在学院党政领导的直接关心支持下，各部门的大力协助下，后勤全体员工的不懈努力下，学院被评为“上海市文明单位”，“上海市安全文明校园”和“上海市平安单位”，“上海市安全生产先进单位”\n" +
            "后勤保卫处根据职责要求，紧紧围绕学院的中心工作，树立全心全意为教学、科研和师生员工服务的观念，坚持“三服务两育人”宗旨，以建立新型的、有特色的后勤保障体系为目标，全处员工团结协作，开拓进取，发扬艰苦创业、无私奉献的精神，努力做好各项工作。后勤队伍更加团结，工作更加主动，作风更加务实，行为更加规范，服务理念进一步明确，服务细节进一步到位，服务品质进一步提升。校容校貌明显改观，窗口服务明显优化，校园管理明显改善，文明程度明显提高，文明素质和精神面貌明显提升。以良好的校园治安安全环境，和学院的稳定安全，保证学院教学、科研、和各项工作正常有序进行。";

    private static String xinxibandetail = "信息办";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_institution);
        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstitutionActivity.this.finish();

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

        mDetailList = new ArrayList<String>();
        mDetailList.add(jiaowuchudetail);
        mDetailList.add(xueshengchudetail);
        mDetailList.add(shixunchudetail);
        mDetailList.add(dudaokeyanchudetail);
        mDetailList.add(renshichudetail);
        mDetailList.add(guojijiaoliuchudetail);
        mDetailList.add(houbaochudetail);
        mDetailList.add(xinxibandetail);

        Resources res = this.getResources();

        mctx = this;

        mListView = (ListView)findViewById(R.id.institution_list);

        mList = new ArrayList<String>();

        mList.add(jiaowuchu);
        mList.add(xueshengchu);
        mList.add(shixunchu);
        mList.add(dudaokeyanchu);
        mList.add(renshichu);
        mList.add(guojijiaoliuchu);
        mList.add(houbaochu);
        mList.add(xinxiban);

        mAdapter = new InstitutionListItemAdapter(mList, mctx, this);

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String detailinfo = mDetailList.get(i);
                Intent intent = new Intent(mctx,
                        InstitutionDetailActivity.class);

                Bundle value = new Bundle();
                value.putString("text",detailinfo);

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

}
