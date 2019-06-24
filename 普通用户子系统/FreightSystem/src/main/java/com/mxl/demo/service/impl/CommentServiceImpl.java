package com.mxl.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxl.demo.entity.Comment;
import com.mxl.demo.mapper.CommentMapper;
import com.mxl.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentMapper commentMapper;
	@Override
	public List<Comment> selectAllByLimit(int start,int pageSize) {
		// TODO Auto-generated method stub
		List<Comment> comment = commentMapper.selectAllByLimit(start, pageSize);
		return comment;
	}
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		int count = commentMapper.selectCount();
		return count;
	}
	@Override
	public int insertComment(Comment comment) {
		// TODO Auto-generated method stub
		int state = commentMapper.insertComment(comment);
		return state;
	}
}
