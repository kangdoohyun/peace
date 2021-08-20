package com.base.newPeaceSystemBuild.interceptor

import com.base.newPeaceSystemBuild.vo.Rq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RoleLevelInterceptor : HandlerInterceptor {
    @Autowired
    private lateinit var rq: Rq

    override fun preHandle(req: HttpServletRequest, resp: HttpServletResponse, handler: Any): Boolean {
        //          화이트 리스트 방식
        val loginedMember = rq.getLoginedMember()!!
        rq.respUtf8()
        println(loginedMember)
//      장례지도사로 등록 신청해서 승인받지 않은 경우
        if (loginedMember.roleLevel != 3 || loginedMember.extra__authenticationStatus != 1) {
            rq.printReplaceJs("장례지도사만 이용할 수 있는 페이지입니다.", "/usr/home/main")

            return false
        }



        return super.preHandle(req, resp, handler)
    }
}