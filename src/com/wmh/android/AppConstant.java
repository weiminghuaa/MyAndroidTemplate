package com.wmh.android;

import java.util.HashMap;

/**
 * 常量类
 * @author ZhangHaoSheng
 * @version v1.0 2012-10-17
 * 
 */
public class AppConstant {
	
	public final static String url="";
	public final static int HTTP_COOKIE_NOT_EXISTS_ERROR=4001;//无法找到cookie
	public final static int HTTP_CLIENT_PROTOCOL_ERROR=4002;//无法建立请求
	public final static int HTTP_ILLEGAL_STATE_ERROR=4003;//
	public final static int HTTP_IO_ERROR=4004;//请求数据失败
	
	
	/**
	 * 软件编号
	 */
	public static final String SOFT_ID = "1";
	
	public static final String OAUTH_CONSUMER_KEY = "e7d60c4c-e7fd-4113-b5d4-7c05d04e87e7";
	public static final String OAUTH_CONSUMER_SECRET = "5923e183-8dd0-430b-84b1-019c5bf28aaf";
	
	//内网
	public static final String DEFAULT_URL = "http://192.168.168.54:5566/";
	
	
	//外网
//	public static final String DEFAULT_URL = "http://res.wlstock.com:8291/";

	/**
	 * 接口请求地址
	 */
	public final static HashMap METHOD_URL = new HashMap();
	static {
		METHOD_URL.put("test", "test/test");//测试
		
		METHOD_URL.put("login", "/account/login");
		METHOD_URL.put("logout", "/account/logout");
		METHOD_URL.put("register", "/account/register");
		METHOD_URL.put("mobilebinder", "/account/mobilebinder");
		
		
		METHOD_URL.put("bindToMobileNumber", "/account/bindToMobileNumber");
		METHOD_URL.put("changePass", "/account/ChangePass");
		METHOD_URL.put("getVerification", "/account/GetVerification");
		METHOD_URL.put("getFunRanking", "/fund/FunRanking"); 
		METHOD_URL.put("getFundDetail", "/fund/GetFundDetail"); 
		METHOD_URL.put("getmessages", "/radar/getmessages");
		METHOD_URL.put("getMessageDetail", "/radar/getMessageDetail");
		METHOD_URL.put("unsubscribe", "/account/unsubscribe");
		METHOD_URL.put("getStockPoolFund", "/StockPool/StockPoolFund");
		METHOD_URL.put("getSingleStockFundInfo", "/StockPool/SingleStockFundInfo");
		METHOD_URL.put("getSoftInfo", "/Software/getSoftInfo");
		METHOD_URL.put("GetStockHoldDetail", "/fund/GetStockHoldDetail");
		METHOD_URL.put("getDetail", "/Fund/GetDetail");
		METHOD_URL.put("mobileregister", "/account/mobileregister");
		METHOD_URL.put("getStockDetail", "/StockPool/GetDetail");
		
		//2.0
		METHOD_URL.put("findpassword", "/account/findpassword");
		METHOD_URL.put("subscribe", "/account/subscribe");
		METHOD_URL.put("repealsubscribe", "/account/repealsubscribe");
		METHOD_URL.put("sendverifycode", "/sendverifycode");
		METHOD_URL.put("fundoverview", "/fund/fundoverview");
		METHOD_URL.put("profitranklist", "/fund/profitranklist");
		METHOD_URL.put("mysubscribe", "/fund/mysubscribe");
		METHOD_URL.put("account", "/fund/account");
		METHOD_URL.put("operated", "/fund/operated");
		METHOD_URL.put("accountsalesreason", "/fund/accountsalesreason");
		METHOD_URL.put("account", "/fund/account");
		METHOD_URL.put("interview", "/fund/interview");
		METHOD_URL.put("stockoperateddetails", "/fund/stockoperateddetails");
		METHOD_URL.put("accountcontrivepoints", "/fund/accountcontrivepoints");
		METHOD_URL.put("interviewdetail", "/fund/interviewdetail");
		METHOD_URL.put("fundinfo", "/fund/fundinfo");
		METHOD_URL.put("chatting", "/fund/chatting");
		METHOD_URL.put("sendmessage", "/fund/sendmessage");
		METHOD_URL.put("funddata", "/fund/funddata");
		METHOD_URL.put("fundrepresentation", "/fund/fundrepresentation");
		METHOD_URL.put("fundlist", "/fund/fundlist");
		METHOD_URL.put("stocksalesreason", "/fund/stocksalesreason");
		
		METHOD_URL.put("subscribeinfo", "/account/subscribeinfo");
		METHOD_URL.put("feedback", "/account/feedback");
		
		METHOD_URL.put("foundation", "/focus/foundation");
		METHOD_URL.put("foundationdetail", "/focus/foundationdetail");
		METHOD_URL.put("emphasis", "/focus/emphasis");
		METHOD_URL.put("emphasisdetail", "/focus/emphasisdetail");
		
		METHOD_URL.put("appshare", "/account/appshare");
		METHOD_URL.put("fundshare", "/account/fundshare");
		METHOD_URL.put("bindingaccount", "/account/bindingaccount");
		METHOD_URL.put("connect", "/account/connect");
		
		METHOD_URL.put("otasetting", "/account/otasetting");
		METHOD_URL.put("customerinfo", "/account/customerinfo");
		
		//3.0
		METHOD_URL.put("poolcomment", "stockpool/comment");
		METHOD_URL.put("pooldetail", "stockpool/stockdetail");
		METHOD_URL.put("poolindex", "stockpool/index");
		METHOD_URL.put("poolnewstock", "stockpool/detail");
		METHOD_URL.put("getallstock", "stockpool/getallstock");
		METHOD_URL.put("getstockinfo", "stockpool/getstockinfo");
		METHOD_URL.put("goodstocktopone", "stockpool/goodstocktopone");
		METHOD_URL.put("getstockpoollist", "stockpool/getstockpoollist");
		METHOD_URL.put("stockquotes", "stockpool/stockquotes");
		METHOD_URL.put("stockpooltape", "stockpool/tape");
		METHOD_URL.put("gettopnumhotstock", "stockpool/gettopnumhotstock");
		
		METHOD_URL.put("messagedetail", "message/detail");
		METHOD_URL.put("messagelist", "message/list");
		METHOD_URL.put("messagequestion", "message/question");
		METHOD_URL.put("messagespirit", "message/spirit");
		
		METHOD_URL.put("customerinvestment","customer/investment");
		METHOD_URL.put("customeraddinvestment", "customer/addinvestment");
		METHOD_URL.put("customeraddstock", "customer/addstock");
		METHOD_URL.put("customerstock", "customer/stock");
		METHOD_URL.put("customerwarning", "customer/warning");
		METHOD_URL.put("customeraddgauntlet", "customer/addgauntlet");
		METHOD_URL.put("customermychallenge", "customer/mychallenger");
		METHOD_URL.put("customermocktrading", "customer/mocktrading");
		METHOD_URL.put("customermockbuy", "customer/mockbuy");
		METHOD_URL.put("getcustomerinfo", "customer/getcustomerinfo");
		METHOD_URL.put("updatecustomerinfoimgurl", "customer/updatecustomerinfo_imgurl");
		METHOD_URL.put("updatecustomerinfoslogan", "customer/updatecustomerinfo_slogan");
		METHOD_URL.put("updatecustomerinfo", "customer/updatecustomerinfo");
		METHOD_URL.put("addstockreminder", "customer/addstockreminder");
		METHOD_URL.put("updatestockreminder", "customer/updatestockreminder");
		METHOD_URL.put("stockholds", "customer/stockholds");
		METHOD_URL.put("customeraccount", "customer/account");
		METHOD_URL.put("customerinvestmentdetail", "customer/investmentdetail");
		
		METHOD_URL.put("mysubscribedetail","fund/newsdetail");
		
		METHOD_URL.put("getthreepic", "software/getthreepic");
		METHOD_URL.put("getonepic", "software/getone");
		
	}
	
	// 未读消息条数广播action
	public final static String UNREADNUMACTION="com.wlstock.fund.unreadnum";
	
	// 注册成功的action
	public final static String REGISTEROK="com.wlstock.fund.register.ok";

	public final static String STOCKTOLOGINACTION="com.wlstock.fund.stocktologin";
	public final static String GUIDETOLOGINACTION="com.wlstock.fund.guidetologin";
	public final static String GUIDETOREGISTERACTION="com.wlstock.fund.guidetoregister";
	
	public final static String FUNDACTIVITY_ACTION = "com.wlstock.fund.ui.FundActivity";
	
	public static final String ACTION_SHOW_NOTIFICATION_DETAIL = "com.wlstock.fund.SHOW_NOTIFICATION_DETAIL";
	
	public final static String INVALID_METHOD = "请求方法为空或不存在";
	
	public final static String LOG_INVALID_URL = "无效的URL或参数";
	
	public static final String INVALID_REQUEST_OR_RESPONSE = "非有效请求或响应！";
	
	// 异常信息
	public final static String INVALID_ARG_ERROR = "参数无效";
	
	public final static String INVALID_NET_ERROR = "网络连接失败";
	
	public final static String PASER_TOJSON_ERROR= "报文格式不正确！";
	
	public final static String CERTIFICATION_ERROR= "签名认证时出错！";
	
	//提示
	public final static String NORECORD_INFO = "暂无信息";
	
	// 模块编号
	
	
	// 权限代码
	public final static String AUTHCODE_SUCC_KEY = "1000"; //有权限访问
	public final static String AUTHCODE_NOLOGIN_KEY = "1001"; // 需要登录才能访问
	public final static String AUTHCODE_NOBIND_KEY = "1002"; // 需要绑定手机才能访问
	public final static HashMap AUTHCODE_MSG = new HashMap();
	static {
		// 有权限 = 1000
		AUTHCODE_MSG.put(AUTHCODE_NOLOGIN_KEY, "未登录。请登录后再查看");
		AUTHCODE_MSG.put(AUTHCODE_NOBIND_KEY, "未绑定手机。请绑定手机后再查看");
		AUTHCODE_MSG.put("1003", "升级至金蟾营用户才能访问");
		AUTHCODE_MSG.put("1004", "升级至金蟾绅用户才能访问");
		AUTHCODE_MSG.put("1005", "升级至金蟾王用户才能访问");
		AUTHCODE_MSG.put("1006", "升级至九五至尊用户才能访问");
	}
	
	// 返回状态码
	public final static HashMap RESPONSE_STATUS = new HashMap();
	static {
		//comm
		RESPONSE_STATUS.put("999", "未知错误");
		RESPONSE_STATUS.put("000", "必要参数为空");
		RESPONSE_STATUS.put("001", "成功");
		RESPONSE_STATUS.put("002", "没有数据返回");
		RESPONSE_STATUS.put("003", "系统内部异常");
		RESPONSE_STATUS.put("004", "操作失败");
		RESPONSE_STATUS.put("005", "账号或者密码错误，或者不匹配");
		RESPONSE_STATUS.put("006", "未登录。请登录后再查看"); // 未登录
		RESPONSE_STATUS.put("007", "未绑定手机。请绑定手机后再查看"); // 未绑定手机
		RESPONSE_STATUS.put("008", "账号已存在"); // 已经存在
		RESPONSE_STATUS.put("009", "升级至金蟾营用户才能访问"); // 不是金蟾营用户
		RESPONSE_STATUS.put("010", "升级至金蟾绅用户才能访问"); // 不是金蟾绅用户
		RESPONSE_STATUS.put("011", "升级至金蟾王用户才能访问"); // 不是金蟾王用户
		RESPONSE_STATUS.put("012", "升级至九五至尊用户才能访问"); // 不是九五至尊用户
		RESPONSE_STATUS.put("013", "无效的验证码");
		
		RESPONSE_STATUS.put("014", "无效的签名");
		RESPONSE_STATUS.put("015", "无效的授权令牌");
		RESPONSE_STATUS.put("016", "无效的APP令牌");
		
		RESPONSE_STATUS.put("10101999", "未知错误");
		RESPONSE_STATUS.put("10100000", "必需参数为空");
		RESPONSE_STATUS.put("10101002", "账号已经存在");
		RESPONSE_STATUS.put("10101003", "账号或者密码错误");
		RESPONSE_STATUS.put("10101004", "操作失败");
		RESPONSE_STATUS.put("10101005", "系统内部异常");
		RESPONSE_STATUS.put("10101006", "未登录");
		
		RESPONSE_STATUS.put("10102002", "没有数据返回");
		RESPONSE_STATUS.put("10102003", "有异常");
		
		RESPONSE_STATUS.put("10103002", "没有数据返回");
		RESPONSE_STATUS.put("10103003", "有异常");
		
		RESPONSE_STATUS.put("10104002", "没有数据返回");
		RESPONSE_STATUS.put("10104003", "有异常");
		
		RESPONSE_STATUS.put("10105001", "成功，有返回值");
		RESPONSE_STATUS.put("10105002", "没有数据返回");
		RESPONSE_STATUS.put("10105003", "有异常");
	}
	
	/**
	 * 数据库保存的最大记录数
	 */
	public static final int DBMAXROWNUM = 200;

	/**
	 * 分页：第一页
	 */
	public static final int FIRSTPAGE = 1;
	
	/**
	 * 分页：每页显示记录数
	 */
	public static final int PAGE_SIZE = 10;
	
	/**
	 * 分页：当前页最小记录，第一页的值为0
	 */
	public static final int CUR_RECORD = 0;
	
	/**
	 * 雷达状态(0:未阅，1：已阅)
	 */
	public static final class MESSAGE_STATUS {
		/**
		 * 未阅
		 */
        public static final int NOREAD = 0;
        
        /**
         * 已阅
         */
        public static final int READED = 1;
        
    }
	
	/**
	 * 消息类型
	 */
	public static final class MESSAGE_TYPE {
		//轻基金交易信息
		public static final int FUND = 1000; 
		//聊聊信息
		public static final int CHAT = 1001;
		//公告信息
		public static final int NOTICE = 1002;
	}
	
	/**
	 * 最新操作类型
	 */
	public static final class NEW_TRADED_TYPE {
		/**
		 * 买入
		 */
		public static final int BUY = 1;
		public static final String BUY_MSG = "买入";
		/**
		 * 卖出
		 */
		public static final int SALE = 0;
		public static final String SALE_MSG = "卖出";
	}
	
	/**
	 * 订阅内容类型
	 */
	public static final class SUBSCRIBE_TYPE {
		/**
		 * 雷达
		 */
		public static String RADAR = "10000";
		
		/**
		 * 轻基金
		 */
		public static String FUND = "10001";
		
		/**
		 * 股票
		 */
		public static String STOCK = "10002";
	}
	
	
	
	
	
	public static final int REQUEST_LOGIN = 1;
	
	public static final class VIEW_DATA {
		public static String TITLE = "TITLE";
		public static String CONTENT = "CONTENT";
		public static String TIME = "TIME";
		public static String AUTHCODE = "AUTHCODE";
	}
	
	/**
	 * 轻基金Icon
	 */
	public static final int[] FUNDIMAGEIDS = { R.drawable.icon_num1_red, R.drawable.icon_num2_red, R.drawable.icon_num3_red,
			R.drawable.icon_num4_red, R.drawable.icon_num5_red, R.drawable.icon_num6_red, R.drawable.icon_num7_red,
			R.drawable.icon_num8_red, R.drawable.icon_num9_red, R.drawable.icon_num10_red };
	
	
	/**
	 * 分组listview中item的position
	 */
	public static final class GroupPosition {
		public static final int SINGLE = 0;//item是组内唯一元素
		public static final int FIRST = 1;//item是组内第一个元素
		public static final int MIDDLE = 2;//item是组内第n个元素
		public static final int LAST = 100;//item是组内最后一个元素
	}
	
	public static final int WEIBOLOGINSUCCESS=0;//微博登陆成功
	public static final int WEIBOLOGINFAIL=1;//微博登陆失败
	
	/**
	 * 微博登陆/分享的类型
	 */
	public static final int SINA=0;//
	public static final int QQ=1;//
	public static final int RENREN=2;//
	public static final int WEIXIN=3;//
	public static final int SMS=4;//
	
	/**
	 * 分享信息的类型
	 */
	public static final int FUNDINFOSHARE=1;//轻基金分享
	public static final int EXPIREINFOSHARE=0;//到期提醒
	
	/**
	 * 各平台的参数
	 */
	public static final String SINA_AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize";
	public static final String SINA_ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
	public static final String SINA_APPKEY = "3793189014";
	public static final String SINA_APPSECRET = "fa28f817805c1a23f8a9797e4d6813e3";
	public static final String SINA_SHARE_URL = "https://api.weibo.com/2/statuses/update.json";
	public static final String QQ_AUTHORIZE_URL = "https://open.t.qq.com/cgi-bin/oauth2/authorize";
	public static final String QQ_ACCESS_TOKEN_URL = "https://open.t.qq.com/cgi-bin/oauth2/access_token";
	public static final String QQ_APPKEY = "801329512";
	public static final String QQ_APPSECRET = "3af78a10eeafddaa464d3f2878471348";
	public static final String QQ_SHARE_URL = "http://open.t.qq.com/api/t/add";
	public static final String WEIXIN_APPID = "wxdc9c6dc72ba6503c";
	public static final String WEIXIN_APPKEY = "7c0a36575d448ba9fcb03689fbe23da7";
	public static final String RENREN_APPKEY = "bef9c5d3572748e0b538d8f70442af00";
	public static final String RENREN_APPSECRET = "dbd4807c28de408a85bf191c7ec3c56c";
	public static final String RENREN_AUTHORIZE_URL = "https://graph.renren.com/oauth/authorize";
	public static final String RENREN_SHARE_URL = "https://api.renren.com/restserver.do";
	public static final String SINA_REDIRECT_URI = "http://www.wlstock.com";
	public static final String QQ_REDIRECT_URI = "http://www.wlstock.com/";
	public static final String RENREN_REDIRECT_URI = "http://graph.renren.com/oauth/login_success.html";
	
	public static final int SERVICESTATUS_NORMAL = 1000;
}
