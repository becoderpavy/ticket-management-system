package com.ticket.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketRequest {

	private Integer id;

	@NotEmpty
	@NotNull
	@Size(min = 10, max = 300, message = "min 10 & max 300 charcter")
	private String subject;

	@NotEmpty
	@NotNull
	@Size(min = 10, max = 300, message = "min 10 & max 300 charcter")
	private String description;

	@NotNull
	private Integer priority;

	@NotNull
	private Integer status;

	@NotNull
	private DepartmentDto department;

	private String comment;

	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class DepartmentDto {
		private Integer id;
		private String name;
	}
}
