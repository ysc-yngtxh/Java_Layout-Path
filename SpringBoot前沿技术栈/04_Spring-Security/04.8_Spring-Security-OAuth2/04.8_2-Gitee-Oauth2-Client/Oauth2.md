提示:标准OAuth2协议可以直接携带 access token 请求用户信息，例如:Gitee。
还有些则需要先获取Openld，再使用 Openld 获取用户信息，例如:微信。

OAuth2UserService 接口有一个默认实现类 DefaultOAuth2UserService ，
可以通过编写默认实现类的子类来实现自定义OAuth2UserService。