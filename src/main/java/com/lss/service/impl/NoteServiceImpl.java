package com.lss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lss.service.INoteService;
import com.lss.domain.entity.Note;
import com.lss.mapper.NoteMapper;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements INoteService {

}
