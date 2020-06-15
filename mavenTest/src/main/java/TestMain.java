import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TestMain {

    public static void main(String[] args) {

        // 请求获取accessToken
        String encode = Base64.encode("11111");
        JSONObject jsonObject = JSONUtil.createObj();
        jsonObject.put("authorization", "Basic " + encode);
        jsonObject.put("grantType", "create_token");
        String result = HttpUtil.createPost("http:")
                .body(jsonObject)
                .execute().body();
        System.out.printf("result" + result);
    }

}