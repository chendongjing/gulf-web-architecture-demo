package cn.chmobile.ai.modules.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.chmobile.ai.model.AjaxJson;
import cn.chmobile.ai.modules.common.jcaptcha.JCaptcha;
import javax.servlet.http.HttpServletRequest;

/**
 * jcaptcha 验证码验证
 * 
 * @author  229260827@qq.com
 *
 */
@Controller
@RequestMapping("/jcaptcha")
public class JCaptchaValidateController {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "validate", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson jqueryValidationEngineValidate(HttpServletRequest request,
			@RequestParam(value = "fieldKey", required = false) String fieldKey,
			@RequestParam(value = "fieldValue", required = false) String fieldValue) {
		AjaxJson ajaxJson = new AjaxJson();
		if (JCaptcha.hasCaptcha(request, fieldValue) == false) {
			ajaxJson.setRet(AjaxJson.RET_FAIL);
			ajaxJson.setMsg(messageSource.getMessage("jcaptcha.validate.error", null, null));
			ajaxJson.setData(fieldKey);

		} else {
			ajaxJson.setRet(AjaxJson.RET_FAIL);
			ajaxJson.setMsg(messageSource.getMessage("jcaptcha.validate.success", null, null));
			ajaxJson.setData(fieldKey);
		}
		return ajaxJson;
	}
}
