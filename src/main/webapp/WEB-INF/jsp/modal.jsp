<!DOCTYPE html>  
<html lang="en">  
<head>  
  <title>Bootstrap Example</title>  
  <meta charset="utf-8">  
  <meta name="viewport" content="width=device-width, initial-scale=1">  
  <style type="text/css">
  .modal-open{overflow:hidden}
.modal{position:fixed;top:0;right:0;bottom:0;left:0;z-index:1050;display:none;overflow:hidden;-webkit-overflow-scrolling:touch;outline:0}
.modal.fade .modal-dialog{-webkit-transition:-webkit-transform .3s ease-out;-o-transition:-o-transform .3s ease-out;transition:transform .3s ease-out;-webkit-transform:translate(0,-25%);-ms-transform:translate(0,-25%);-o-transform:translate(0,-25%);transform:translate(0,-25%)}.modal.in .modal-dialog{-webkit-transform:translate(0,0);-ms-transform:translate(0,0);-o-transform:translate(0,0);transform:translate(0,0)}.modal-open .modal{overflow-x:hidden;overflow-y:auto}
.modal-dialog{position:relative;width:auto;margin:10px}
.modal-content{position:relative;background-color:#fff;-webkit-background-clip:padding-box;background-clip:padding-box;border:1px solid #999;border:1px solid rgba(0,0,0,.2);border-radius:6px;outline:0;-webkit-box-shadow:0 3px 9px rgba(0,0,0,.5);box-shadow:0 3px 9px rgba(0,0,0,.5)}
.modal-backdrop{position:fixed;top:0;right:0;bottom:0;left:0;z-index:1040;background-color:#000}
.modal-backdrop.fade{filter:alpha(opacity=0);opacity:0}.modal-backdrop.in{filter:alpha(opacity=50);opacity:.5}
.modal-header{padding:15px;border-bottom:1px solid #e5e5e5}
.modal-header .close{margin-top:-2px}
.modal-title{margin:0;line-height:1.42857143}
.modal-body{position:relative;padding:15px}
.modal-footer{padding:15px;text-align:right;border-top:1px solid #e5e5e5}
.modal-footer .btn+.btn{margin-bottom:0;margin-left:5px}
.modal-footer .btn-group .btn+.btn{margin-left:-1px}
.modal-footer:after,.modal-footer:before,.modal-header:after,.modal-header:before,.modal-footer:after,.modal-header:after{clear:both}
.modal-footer .btn-block+.btn-block{margin-left:0}.modal-scrollbar-measure{position:absolute;top:-9999px;width:50px;height:50px;overflow:scroll}@media (min-width:768px){
.modal-dialog{width:600px;margin:30px auto}
.modal-content{-webkit-box-shadow:0 5px 15px rgba(0,0,0,.5);box-shadow:0 5px 15px rgba(0,0,0,.5)}
.modal-sm{width:300px}}@media (min-width:992px){
.modal-lg{width:900px}}
  </style>
 
</head>  
<body>  
  
<div class="container">  
  <h2>Modal Example</h2>  
  <!-- Trigger the modal with a button -->  
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>  
  
  <!-- Modal -->  
  <div class="modal fade" id="myModal"  role="dialog">>
    	<div class="modal-dialog modal-lg">
      		<div class="modal-content">
        	<!-- Modal Header -->
        		<div class="modal-header">
          			<h4 class="modal-title primary">Load Calculator</h4>
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
        		</div>
        
        		<!-- Modal body -->
        		<div class="modal-body">
         			Modal body..
        		</div>
        
		        <!-- Modal footer -->
		        <div class="modal-footer">
		          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		        </div>
        	</div>
    	</div>
  	</div> 
    
</div>  

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>  
</html>  

 
