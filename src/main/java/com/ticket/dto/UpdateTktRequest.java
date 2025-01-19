package com.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateTktRequest {
	
	private String ticketId;
	
	private Integer status;
	
	private String comment;
	
}
