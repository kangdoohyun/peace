package com.base.newPeaceSystemBuild.controller

import com.base.newPeaceSystemBuild.service.GenFileService
import com.base.newPeaceSystemBuild.service.MemberRoleService
import com.base.newPeaceSystemBuild.service.MemberService
import com.base.newPeaceSystemBuild.vo.Rq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartRequest


@Controller
class UsrDirectorController(
    private val memberService: MemberService,
    private val genFileService: GenFileService,
    private val memberRoleService: MemberRoleService
) {
    @Autowired
    private lateinit var rq: Rq;

    // VIEW Mapping 함수 시작
    @RequestMapping("/usr/director/request")
    fun showRequest(): String {
        return "usr/director/request"
    }

    @RequestMapping("/usr/director/modify")
    fun showModify(): String {
        return "usr/director/modify"
    }
    // VIEW Mapping 함수 끝

    // VIEW 기능 함수 시작
    @RequestMapping("/usr/director/doRequest", method = [RequestMethod.POST])
    @ResponseBody
    fun doRequest(
        introduce: String,
        multipartRequest: MultipartRequest
    ): String {
        // Request 페이지에서 넘어온 파라미터를 DB에 추가하는 과정
        // 장례지도사 승인 신청 시, roleLevel(=roleId)는 3(장례지도사)가 되고, authenticationStatus에 따라서 이후 구분된다.

        val fileMap = multipartRequest.fileMap
        for (fileInputName in fileMap.keys) {
            val multipartFile = fileMap[fileInputName]

            if (multipartFile != null) {
                // 파일을 저장
                genFileService.save(multipartFile, rq.getLoginedMember()!!.id)
            }
        }

        //  장례지도사의 roleLevel(roleId)는 3
        //  승인 요청 시, roleLevel은 장례지도사가 되고, authenticationStatus로 구분된다.
        memberRoleService.insertDataIntoMemberRole(introduce, rq.getLoginedMember()!!.id, 3)
        memberService.updateRoleLevel(rq.getLoginedMember()!!.id, 3)

        // 장례지도사 신청은 회원데이터 수정이기 때문에 세션데이터를 수정된값으로 다시 넣어준다.
        rq.login(memberService.getMemberById(rq.getLoginedMember()!!.id)!!)

        return rq.replaceJs("장례지도사 영업신청이 완료되었습니다.", "../home/main")
    }
    // VIEW 기능 함수 끝
}