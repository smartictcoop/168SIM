<%@ page language="java" import="java.util.*,java.text.DecimalFormat"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>168CHECK</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="css/se.css" rel="stylesheet">

</head>
<body>
	<p>
		<br />
	</p>
	<div class="container">
		<h1 class="caption">168 SIM</h1>

		<h2>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#exampleModal1" data-whatever="@fat">อัพเดทเบอร์ในระบบ</button>
		</h2>

		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel">


			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">เพิ่มเบอร์เข้าระบบ</h4>
					</div>
					<div class="modal-body">
						<form>

							<label class="control-label">กรุณาเลือกไฟล์</label> <input
								id="input-1" type="file" class="file" />


						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary">Save</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel">


			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">แก้ไขเบอร์ในระบบ</h4>
					</div>
					<div class="modal-body">
						<form>

							<label class="control-label">กรุณาเลือกไฟล์</label> <input
								id="input-1" type="file" class="file" />


						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary">Save</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel">


			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">ลบเบอร์ในระบบ</h4>
					</div>
					<div class="modal-body">
						<form>

							<label class="control-label">กรุณาเลือกไฟล์</label> <input
								id="input-1" type="file" class="file" />


						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary">Save</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						
					</div>
				</div>
			</div>
		</div>


		<table class="table table-striped table-bordered table-hover"
			id="mydata">
			<thead>
				<tr>
					<th class="text-center">SIM</th>
					<th class="text-center">MSISDN</th>
					<th class="text-center">BRANCH</th>
					<th class="text-center">STATUS</th>
					<th class="text-center">DATE_INSERT</th>
					<th class="text-center">REGISTER_DATE</th>
					<th class="text-center">REGISTER_BRANCH</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="userList" var="ul">
					<tr>
						<td><s:property value="#ul.SIM"/></td>
						<td><s:property value="#ul.MSISDN"/></td>
						<td><s:property value="#ul.BRANCH"/></td>
						<td><s:property value="#ul.STATUS"/></td>
						<td><s:property value="#ul.DATE_INSERT"/></td>
						<td><s:property value="#ul.REGISTER_DATE"/></td>
						<td><s:property value="#ul.REGISTER_BRANCH"/></td>
						
					</tr>
				</s:iterator>
			</tbody>
		</table>



	</div>




	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/dataTables.bootstrap.min.js"></script>
	<script>
		$('#mydata').dataTable();
	</script>
</body>
</html>