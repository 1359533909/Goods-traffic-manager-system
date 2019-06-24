package com.mxl.demo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.mxl.demo.entity.Comment;

public interface CommentService {
	public List<Comment> selectAllByLimit(int start,int pageSize);
	public int selectCount();
	public int insertComment(Comment comment);
}
