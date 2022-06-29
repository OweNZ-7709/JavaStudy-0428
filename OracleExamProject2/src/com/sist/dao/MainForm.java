package com.sist.dao;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sist.curd.BoardDAO;
import com.sist.curd.BoardVO;

public class MainForm extends JFrame implements ActionListener{
    CardLayout card=new CardLayout();
    LoginForm login=new LoginForm();
    BoardForm bf=new BoardForm();
    InsertForm in=new InsertForm();
    UpdateForm up=new UpdateForm();
    DetailForm df=new DetailForm();
    public MainForm()
    {
    	setLayout(card);
    	add("LOGIN",login);
    	add("DF",df);
    	add("UP",up);
    	add("IN",in);
    	add("BF",bf);
    	
    	setSize(800, 660);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	// 등록 
    	login.b1.addActionListener(this);
    	login.b2.addActionListener(this);
    	
    	// 게시판 메인 
    	bf.b5.addActionListener(this);// 새글 
    	
    	// 추가 
    	in.b1.addActionListener(this);// 글쓰기
    	in.b2.addActionListener(this);// 글쓰기 취소
    	
    	boardListData();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new MainForm();
	}
	
	public void boardListData()
	{
		BoardDAO dao=new BoardDAO();
		List<BoardVO> list=dao.boardListData();
		for(int i=bf.model.getRowCount()-1;i>=0;i--)
		{
			bf.model.removeRow(i);
		}
		
		for(BoardVO vo:list)
		{
			String[] data={
				String.valueOf(vo.getNo()),
				vo.getSubject(),
				vo.getName(),
				vo.getRegdate().toString(),
				String.valueOf(vo.getHit())
			};
			
			bf.model.addRow(data);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 자바스크립트
		if(e.getSource()==login.b1)
		{
			String id=login.tf.getText();
			// let id=login.tf.value  let a=[] let a={}
			// var => let , 람다식  =>
			if(id.trim().length()<1) // 유효성 검사 
			{
				JOptionPane.showMessageDialog(this, "ID를 입력하세요");
				login.tf.requestFocus();
				return;
			}
			
			String pwd=String.valueOf(login.pf.getPassword());
			if(pwd.trim().length()<1)
			{
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
				login.pf.requestFocus();
				return;
			}
			
			// 로그인 확인 
			BoardDAO dao=new BoardDAO();
			String result=dao.isLogin(id, pwd);
			
			if(result.equals("NOID"))
			{
				JOptionPane.showMessageDialog(this, "아이디가 존재하지 않습니다");
				login.tf.setText("");
				login.pf.setText("");
				login.tf.requestFocus();
			}
			else if(result.equals("NOPWD"))
			{
				JOptionPane.showMessageDialog(this, "비밀번호가 틀립니다");
				login.pf.setText("");
				login.pf.requestFocus();
			}
			else
			{
				// 화면 이동 
				card.show(getContentPane(), "BF");
				// sendRedirect("main.jsp")
			}
			
		}
		else if(e.getSource()==login.b2)
		{
			System.exit(0);
		}
		// 새글로 이동 
		else if(e.getSource()==bf.b5)
		{
			in.tf1.setText("");
			in.tf2.setText("");
			in.ta.setText("");
			in.pf.setText("");
			
			
			card.show(getContentPane(), "IN");
			in.tf1.requestFocus();
		}
		// 글쓰기 버튼 클릭
		else if(e.getSource()==in.b1)
		{
			String name=in.tf1.getText();
			String subject=in.tf2.getText();
			String content=in.ta.getText();
			String pwd=String.valueOf(in.pf.getPassword());
			BoardVO vo=new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			BoardDAO dao=new BoardDAO();
			dao.boardInsert(vo);
			
			card.show(getContentPane(),"BF");// 목록 이동 
			boardListData();
		}
		// 글쓰기 취소 버튼
		else if(e.getSource()==in.b2)
		{
			card.show(getContentPane(), "BF");
			// <input type=button value="취소"
			//  onclick="javascript:history.back()">
		}
	}

}