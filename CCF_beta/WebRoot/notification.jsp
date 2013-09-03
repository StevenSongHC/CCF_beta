<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

	<s:iterator value="notification">
		<s:iterator value="notificationItem">
			<s:property value="sender"/>
			<s:property value="action"/>
			<s:property value="date"/>
		</s:iterator>
	</s:iterator>
