package com.spring.web.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.MemoContentsDto;
import com.spring.web.service.MemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("MemoController V1")
@RestController
@RequestMapping("/memo")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class MemoController {
	
	@Autowired
	private MemoService memoService;
	
	/**
	 * 메모 작성하기
	 * 
	 * @param MemoContentsDto(memoId, memoContent, memoLink)
	 * @return int(0: 실패, 1:t성공)
	 */
	@ApiOperation(value = "메모 작성하기", notes ="@param : MemoContentsDto(memoId, memoContent, memoLink)  </br> @return int(0: 실패, 1:t성공)")
	@PostMapping
	public ResponseEntity<Integer> writeMemo(@RequestBody MemoContentsDto memo) {
		int result = memoService.writeMemo(memo);
		if(result == 1)
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		else
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 해당 유저의 메모 리스트 가져오기
	 * 
	 * @param memoId
	 * @return List<MemoContentsDto>
	 */
	@ApiOperation(value = "해당 유저의 메모 리스트 가져오기", notes =" @param memoId  </br> @return List<MemoContentsDto>")
	@GetMapping("{memoId}")
	public ResponseEntity<List<MemoContentsDto>> listMemo(@PathVariable String memoId) throws Exception {
		try {
			List<MemoContentsDto> list = memoService.listMemo(memoId);
			Collections.sort(list, new Comparator<MemoContentsDto>() {
				@Override
				public int compare(MemoContentsDto o1, MemoContentsDto o2) {
					return o2.getMemoTime().compareTo(o1.getMemoTime());
				}
			});
			return new ResponseEntity<List<MemoContentsDto>>(list, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 메모 수정하기
	 * 
	 * @param MemoContentsDto(memoNum, memoContent)
	 * @return List<MemoContentsDto>
	 */
	@ApiOperation(value = "메모 수정하기", notes =" @param MemoContentsDto(memoId, memoNum, memoContent)  </br> @return List<MemoContentsDto>")
	@PutMapping
	public ResponseEntity<List<MemoContentsDto>> modifyMemo(@RequestBody MemoContentsDto memo) throws Exception {
		int result = memoService.modifyMemo(memo);
		if(result != 0)
			return new ResponseEntity<List<MemoContentsDto>>(memoService.listMemo(memo.getMemoId()), HttpStatus.OK);
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 메모 삭제하기
	 * 
	 * @param memoId, memoNum
	 * @return List<MemoContentsDto>
	 */
	@ApiOperation(value = "메모 삭제하기", notes =" @param memoId, memoNum  </br> @return List<MemoContentsDto>")
	@DeleteMapping("{memoId}/{memoNum}")
	public ResponseEntity<List<MemoContentsDto>> deleteMemo(@PathVariable String memoId, @PathVariable int memoNum) throws Exception {
		int result = memoService.deleteMemo(memoNum);
		if(result != 0)
			return new ResponseEntity<List<MemoContentsDto>>(memoService.listMemo(memoId), HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
