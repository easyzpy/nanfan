package com.randing.system.domain.vo;

import com.randing.system.domain.vo.base.BasePage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteReqDTO extends BasePage implements Serializable{

    private static final long serialVersionUID = -3990179777478809155L;

    //    private Long id;
    private String res;
    private String age;
}
