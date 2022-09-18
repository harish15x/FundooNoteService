package com.bridgelabz.note.service;

import com.bridgelabz.note.dto.LableDTO;
import com.bridgelabz.note.model.LableModel;
import com.bridgelabz.note.util.ResponseClass;

import java.util.List;

public interface ILableService {

    ResponseClass addLable(LableDTO lableDTO);

    ResponseClass updateLable(String token, long lableId, LableDTO lableDTO);

   List<LableModel> getLableData(String token);

    ResponseClass deleteLable(String token, long lableId);

    ResponseClass addLabel(long labelId, String token, List<Long> noteId);


}
