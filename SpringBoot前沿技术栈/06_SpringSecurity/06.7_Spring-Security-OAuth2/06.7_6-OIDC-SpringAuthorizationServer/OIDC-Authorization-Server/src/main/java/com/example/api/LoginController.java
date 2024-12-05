package com.example.api;

import com.example.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-01 22:01
 * @apiNote TODO
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @Autowired
    private ClientService clientService;

    @GetMapping("/client")
    public String authorizedClients(HttpServletRequest request,
                                    Principal principal) {
        // 根据 principalName 查找已授权的应用列表
        String principalName = principal.getName();
        List<RegisteredClient> registeredClientsByPrincipalNames = clientService.findRegisteredClientsByPrincipalName(principalName);
        // registeredClients 集合存入 request 域
        request.setAttribute("registeredClients", registeredClientsByPrincipalNames);
        // 返回 authorizedClients.html
        return "authorizedClients";
    }

    @DeleteMapping("/undo")
    public String undo(HttpServletRequest request,
                       @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
                       Principal principal) {
        String result = "Success";
        try {
            clientService.revokeAuthorization(clientId, principal.getName());
        } catch (Exception e) {
            result = "Fail";
            e.printStackTrace();
        }
        // 操作结果存入 request 域
        request.setAttribute("result", result);
        // 返回 revokeResult.html
        return "revokeResult";
    }
}
