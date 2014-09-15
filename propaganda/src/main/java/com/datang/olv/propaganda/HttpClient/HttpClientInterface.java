package com.datang.olv.propaganda.HttpClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by l on 14-9-14.
 */
public class HttpClientInterface {
    private static final String TAG = "HttpClientInterface";
    private static final String API_URL ="http://119.254.110.62";

    interface ILogin {
//        @Headers({
//                "User-Agent: Tour-BrightApp",
//                "Content-Type:application/json",
//                "Content-Encoding:UTF-8"
//        })
        @POST("/auth/local")
        void login(@Body User user, Callback<LoginResult> cb);
    }

    public static void Userlogin(String email, String password, Callback<LoginResult> cb) {
        User userinfo = new User();
        userinfo.user = new UserData(email, password);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
//        RestAdapter.Builder builder = new RestAdapter.Builder();
//        builder.setConverter(new Converter() {
//
//            @Override
//            public TypedOutput toBody(Object arg0) {
//                // TODO Auto-generated method stub
//                return null;
//            }
//
//            @Override
//            public LoginResult fromBody(TypedInput body, Type type)
//                    throws ConversionException {
//                // TODO Auto-generated method stub
//                if (body.length() > 0){
//                    byte[] buf = new byte[(int) body.length()];
//                    try {
//                        body.in().read(buf);
//                        String str = new String(buf,"GBK");
//
//                        int nindex = str.indexOf("({");
//                        String strreturn = str.substring(nindex+1, str.length()-2);
//                        return new Gson().fromJson(strreturn,LoginResult.class);
//
//                        //int i=10;
//                        //i=i+1;
//                    }catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//                return null;
//            }
//        });
//        RestAdapter restAdapter = builder.setEndpoint(API_URL).setLogLevel(RestAdapter.LogLevel.FULL).build();

        ILogin lc = restAdapter.create(ILogin.class);
        lc.login(userinfo, cb);
    }

    static class User {
        UserData user;
    }

    static class UserData {
        String email;
        String password;

        UserData(String id, String password) {
            this.email = id;
            this.password = password;
        }


        @Override
        public String toString() {
            return email + ":" + password;
        }
    }

    public static class LoginResult {
        AuthToken data;
        public String getToken() {
            return data.auth_token;
        }
    }

    static class AuthToken {
        String auth_token;
    }


//
//        @Headers({
//                "Accept: application/json",
//                "User-Agent: Tour-BrightApp",
//                "Content-Type:application/json",
//                "Content-Encoding:UTF-8"
//        })
//        @POST("/api/registrations")
//        void regist(@Body User user, Callback<Result> cb);
//    }

    interface SchoolInfo{
        @Headers({
                "Accept: application/json",
                "User-Agent: Tour-BrightApp",
                "Content-Type:application/json",
                "Content-Encoding:UTF-8"
        })
        @GET("/api/schools/")
        void GetSchoolInfo(Callback<Schoolinfo> cb);
    }
    //http://119.254.110.62/api/schools/
    public static void GetSchoolInfo(Callback<Schoolinfo> cb) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        RestAdapter restAdapter = builder.setEndpoint(API_URL).setLogLevel(RestAdapter.LogLevel.FULL).build();

        SchoolInfo ic = restAdapter.create(SchoolInfo.class);
        ic.GetSchoolInfo(cb);

        //GetSchoolInfo(cb);
    }

    public static class Schoolinfo {
        public List<historiesinfo> histories;
        public List<leadersinfo>   leaders;
        public List<instituesinfo> institues;
        public List<majorsinfo>    majors;
    }

    public class historiesinfo{
        String year;
        String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }
    }

    public class leadersinfo{
        String detail;
        String desc;
    }
    public class instituesinfo{
        String detail;
        String name;
    }

    public class majorsinfo{
        String detail;
        String desc;
        String name;
    }


    interface CoursesInfo{
        @Headers({
                "Accept: application/json",
                "User-Agent: Tour-BrightApp",
                "Content-Type:application/json",
                "Content-Encoding:UTF-8"
        })
        @GET("/api/courses/")
        void GetCoursesInfo(@Header("Authorization") String authorization, Callback<List<Courses>> cb);
    }

    public static void GetCoursesInfo(String authorization, Callback<List<Courses>> cb) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
//        builder.setConverter(new Converter() {
//
//            @Override
//            public TypedOutput toBody(Object arg0) {
//                // TODO Auto-generated method stub
//                return null;
//            }
//
//            @Override
//            public CoursesDatainfo fromBody(TypedInput body, Type type)
//                    throws ConversionException {
//                // TODO Auto-generated method stub
//                if (body.length() > 0){
//                    byte[] buf = new byte[(int) body.length()];
//                    try {
//                        body.in().read(buf);
//                        String str = new String(buf,"GBK");
//
//                        StringBuffer strbuffer = new StringBuffer();
//                        strbuffer.append("{");
//                        strbuffer.append('\"');
//                        strbuffer.append("cur");
//                        strbuffer.append('\"');
//                        strbuffer.append(" : ");
//
//                        strbuffer.append(str);
//                        strbuffer.append("}");
//                        String strhead = strbuffer.toString();
//
//                        //String strreturn = strhead+str;
//                        return new Gson().fromJson(strhead,CoursesDatainfo.class);
//
//                        //int i=10;
//                        //i=i+1;
//                    }catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//                return null;
//            }
//        });

        RestAdapter restAdapter = builder.setEndpoint(API_URL).setLogLevel(RestAdapter.LogLevel.FULL).build();

        CoursesInfo ic = restAdapter.create(CoursesInfo.class);

        String author = "Bearer " + authorization;

        ic.GetCoursesInfo(author , cb);

        //GetSchoolInfo(cb);
    }

    public static class Courses{
        String _id;
        String name;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        String info;
        List<String> owners;
        List<String> lectureAssembly;
    }

//    public class CLassesinfo{
//        String _id;
//        String name;
//        String orgid;
//        String yearGrade;
//    }
}
