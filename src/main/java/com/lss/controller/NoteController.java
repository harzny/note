package com.lss.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lss.utils.EncriptionUtil;
import com.lss.utils.JWTUtil;
import com.lss.domain.data.AjaxResult;
import com.lss.domain.entity.Note;
import com.lss.domain.entity.User;
import com.lss.service.INoteService;
import com.lss.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController extends BaseController {

    @Qualifier("INoteService")
    @Autowired
    private INoteService iNoteService;
    @Qualifier("IUserService")
    @Autowired
    private IUserService iUserService;

    @PostMapping
    public AjaxResult create(@Validated Note note, HttpServletRequest req) {
        String userId = JWTUtil.getUserId(req);
        note.setUserId(userId);
        boolean b = iNoteService.save(note);
        return b ? success("添加成功", note) : error("添加失败", null);
    }

    /**
     * 获取note信息
     * @param req
     * @return
     */
    @GetMapping
    public AjaxResult queryLsit(HttpServletRequest req, Note note) {
        String userId = JWTUtil.getUserId(req);
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.like(note.getNoteTitle() != null && note.getNoteTitle().length() > 0, "note_title", note.getNoteTitle());
        wrapper.eq(note.getClassifyId() != null && note.getClassifyId().length() > 0, "classify_id", note.getClassifyId());
        wrapper.ne("upt_act", "D");
        List<Note> notes = iNoteService.list(wrapper);
        return success(notes);
    }

    /**
     * 通过noteId查找单个便签的内容
     *
     * @param noteId
     * @return
     */
    @GetMapping("/{noteId}")
    public AjaxResult queryNoteById(@PathVariable String noteId) {
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.eq("note_id", noteId);
        wrapper.ne("upt_act", "D");
        Note nowNote = iNoteService.getOne(wrapper);
        return success(nowNote);
    }

    @PutMapping
    public AjaxResult update(Note note) {
        boolean b = iNoteService.updateById(note);
        return b ? success("修改成功", note) : error("修改失败", null);
    }

    @DeleteMapping("/{noteId}")
    public AjaxResult delete(@PathVariable String noteId) {
        Note note = iNoteService.getById(noteId);
        note.setUptAct("D");
        boolean b = iNoteService.updateById(note);
        return b ? success("删除成功",null) : error("删除失败", null);

    }

    /**
     * 恢复删除
     * @param noteId
     * @return
     */
    @PostMapping("/recoverDeleted/{noteId}")
    public AjaxResult recoverDeleted(@PathVariable String noteId) {
        Note note = iNoteService.getById(noteId);
        note.setUptAct("U");
        note.setClassifyId("");
        boolean save = iNoteService.updateById(note);
        return save ? success(null) : error(null);
    }

    /**通过点击保险箱查询有加密的便签
     * @param req
     * @return
     */
    @GetMapping("/queryEncryption")
    public AjaxResult queryEncryption(HttpServletRequest req) {
        //获取保存信息
        String userId=JWTUtil.getUserId(req);
        QueryWrapper<Note> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.like("is_encryption",1);
        wrapper.ne("upt_act","D");
        List<Note> notes=iNoteService.list(wrapper);
        return success(notes);
    }

    /**
     * 加密或取消加密
     * @param encryption
     * @param noteId
     * @param req
     * @return
     */
    @PostMapping("/verify")
    public AjaxResult verify(String encryption, String noteId, HttpServletRequest req) {
        //获取用户id做密码验证
        User nowUser = iUserService.getUserByReq(req);
        boolean b = EncriptionUtil.validatePwd(encryption, nowUser.getEncryption());
        if (b) {
            Note note = iNoteService.getById(noteId);
            if (note != null && note.getIsEncryption()==0  ){
                note.setIsEncryption( 1);
                boolean b1 = iNoteService.updateById(note);
                return b1 ? success("加密成功",null) : error("加密失败",null);
            }else{
                note.setIsEncryption(0);
                boolean b1 = iNoteService.updateById(note);
                return b1 ? success("取消成功",null) : error("取消失败",null);
            }
        }
        return error("密码错误", null);
    }
    @PostMapping("/setTop")
    public AjaxResult setTop( String noteId) {
        Note note = iNoteService.getById(noteId);
        if (note.getIsTop()==0) {
            note.setIsTop(1);
            boolean b = iNoteService.updateById(note);
            return b ? success("置顶成功", note) : error("置顶失败", null);
        }else{
            note.setIsTop(0);
            boolean b = iNoteService.updateById(note);
            return b ? success("取消成功", note) : error("取消失败", null);
        }
    }
    /**
     * 添加便签到分类
     * @param note
     * @return
     */
    @PostMapping("/setClassify")
    public AjaxResult setClassify(Note note) {
//         Note note1=iNoteService.getById(note.getNoteId());
//         note.setClassityId(note.getClassityId());
        boolean b = iNoteService.updateById(note);
        return b ? success("修改成功", note) : error("修改失败", null);
    }

    /**
     * 查询已经删除的便签
     * @param req
     * @return
     */
    @GetMapping("/queryDeleteList")
    public AjaxResult queryDeleteList(HttpServletRequest req) {
        String userId = JWTUtil.getUserId(req);
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("upt_act", "D");
        List<Note> notes = iNoteService.list(wrapper);
        return success(notes);
    }
}
