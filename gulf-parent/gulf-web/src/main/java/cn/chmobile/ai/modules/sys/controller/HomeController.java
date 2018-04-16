package cn.chmobile.ai.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value =  "/home")
public class HomeController {

    /**
     * 主页面
     */
    @RequestMapping
    public String showHome() {

        return "home";
    }

    /**
     * 全网健康度,登录成功展示
     */
    @RequestMapping("wholeNetFacts")
    public String showHomePage() {

      return "modules/wholeNetFacts";
    }
    
    /**
     * 根因定位问题-小区画像
     */
    @RequestMapping("problemDistPlot")
    public String problemDistPlot() {
    	return "modules/causeLocation/problemDistPlot/problemDistPlot";

    }
    
    /**
     * 根因定位问题-小区列表
     */
    @RequestMapping("problemDistList")
    public String problemDistList() {
    	return "modules/causeLocation/problemDistList/problemDistList";

    }
    
    /**
     * 网络结构优化-全网画像
     */
    @RequestMapping("allNetPlot")
    public String allNetPlo() {
    	return "modules/netStructOpt/allNetPlot/allNetPlot";

    }
    
    /**
     * 网络结构优化-弱覆盖小区
     */
    @RequestMapping("weakCoverageDist")
    public String weakCoverageDist() {
    	return "modules/netStructOpt/weakCoverageDist/weakCoverageDist";

    }
    
    /**
     * 网络结构优化-过度覆盖小区
     */
    @RequestMapping("overlayDist")
    public String overlayDist() {
    	return "modules/netStructOpt/overlayDist/overlayDist";

    }
    
    /**
     * 网络结构优化-重复覆盖区域
     */
    @RequestMapping("repeatCoverageDist")
    public String repeatCoverageDist() {
    	return "modules/netStructOpt/repeatCoverageDist/repeatCoverageDist";

    }
    
    /**
     * 网络结构优化-空洞覆盖区域
     */
    @RequestMapping("emptyCoverageDist")
    public String emptyCoverageDist() {
    	return "modules/netStructOpt/emptyCoverageDist/emptyCoverageDist";

    }
    
    /**
     * 网络结构优化-优化小区列表
     */
    @RequestMapping("optimizeList")
    public String optimizeList() {
    	return "modules/netStructOpt/optimizeList/optimizeList";

    }
    
    /**
     * 系统设置-用户管理
     */
    @RequestMapping("userManager")
    public String userManager() {
    	return "modules/sys/user/userManager";

    }
    
    /**
     * 系统设置-操作日志
     */
    @RequestMapping("optionLogs")
    public String optionLogs() {
    	return "modules/sys/logs/optionLogs";

    }
}
