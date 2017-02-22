package com.yiran.controllers;

import com.yiran.constans.SysConstant;
import com.yiran.entities.User;
import com.yiran.repositories.UserRepository;
import com.yiran.result.IMException;
import com.yiran.service.UserService;
import com.yiran.workflow.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yiran on 17-1-25.
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private WorkflowService workflowService;

    @RequestMapping("/home")
    public String home(Model model, HttpSession session) throws IMException {
        User user = userService.currentUser();
        if (user != null) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("workCode", user.getWorkCode());
        }
        model.addAttribute("currentYear",SysConstant.CURRENT_YEAR);
        model.addAttribute("status", workflowService.getStatus(14L));
        return userView(user);
    }

    private void addData(User user, Model model) {
        if (SysConstant.ROLE_STAFF.equals(user.getRole())) {
            //do nothing;
        } else if (SysConstant.ROLE_BANK.equals(user.getRole())) {
            //do nothing;
        } else if (SysConstant.ROLE_AGENT.equals(user.getRole())) {
            //do nothing;
        } else if (SysConstant.ROLE_LEADER.equals(user.getRole())) {
            model.addAttribute("staffList", getStaffsByLeaderUser(user));
        } else if (SysConstant.ROLE_HR.equals(user.getRole())) {
            //do nothing;
        } else if (SysConstant.ROLE_ADMIN.equals(user.getRole())) {
            //do nothing;
        }
    }

    @Autowired
    private UserRepository userRepository;

    private List<User> getStaffsByLeaderUser(User user) {
        if (SysConstant.ROLE_LEADER.equals(user.getRole())) {
            List<User> userList = userRepository.findByBranchCode(user.getBranchCode());
            List<User> list = new ArrayList<User>();
            for (User u : userList) {
                if (SysConstant.ROLE_STAFF.equals(u.getRole()) || SysConstant.ROLE_BANK.equals(u.getRole()) || SysConstant.ROLE_AGENT.equals(u.getRole())) {
                    list.add(u);
                }
            }
            return list;
        }
        return null;
    }

    private String userView(User user) {
        if (user != null && user.getRole() != null) {
            if ("ROLE_STAFF".equals(user.getRole())) {
                return "staffView";
            }
            if ("ROLE_BANK".equals(user.getRole())) {
                return "bankView";
            }
            if ("ROLE_AGENT".equals(user.getRole())) {
                return "agentView";
            }
            if ("ROLE_LEADER".equals(user.getRole())) {
//                return "testView";
                return "leaderView";
            }
            if ("ROLE_HR".equals(user.getRole())) {
                return "hrView";
            }
            if ("ROLE_ADMIN".equals(user.getRole())) {
                return "adminView";
            }
        }
        return "staffView";
    }


    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
}
