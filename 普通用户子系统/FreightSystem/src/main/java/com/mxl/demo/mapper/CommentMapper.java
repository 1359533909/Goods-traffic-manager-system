package com.mxl.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mxl.demo.entity.Comment;

public interface CommentMapper {
	public List<Comment> selectAllByLimit(@Param("start") int start, @Param("pageSize") int pageSize);
	public int selectCount();
	public int insertComment(Comment comment);
}
