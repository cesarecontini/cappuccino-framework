package com.github.cesarecontini.cappuccino.framework.web;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CappuccinoCssClasses
{
	private String container = "container";
	private String row = "row";
	private String column = "col-md";
	private String columnOneHalf = "col-md-6";
	private String columnOneThird = "col-md-4";
	private String columnTwoThirds = "col-md-8";

	private String h1 = "";
	private String h2 = "";

	private String img = "img-fluid";
	private String table = "table";

	private String buttonPrimary = "btn btn-primary";
	private String buttonSecondary = "btn btn-secondary";

	private String formError = "needs-validation was-validated";
	private String inputGroup = "form-group";
	private String inputGroupError = "";
	private String inputCheckboxGroup = "form-check";
	private String inputCheckboxGroupInline = "form-check form-check-inline";
	private String inputControl = "form-control form-control-lg";
	private String inputCheckboxControl = "form-check-input";
	private String inputCheckboxlabel = "form-check-label";
	private String inputLabel = "";
	private String inputTextHint = "form-text text-muted";
	private String inputErrorHint = "invalid-feedback";
	private String breadcrumbList = "breadcrumb";
	private String breadcrumbItem = "breadcrumb-item";
	private String breadcrumbItemActive = "breadcrumb-item active";
}
