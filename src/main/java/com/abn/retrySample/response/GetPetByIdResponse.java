package com.abn.retrySample.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPetByIdResponse extends BaseResponse {

    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;
    private int code;
    private String type;
    private String message;

}
