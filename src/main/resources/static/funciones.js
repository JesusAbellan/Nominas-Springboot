function eliminar(id) {
	swal({
		title: "¿Estás seguro?",
		text: "Una vez lo borres, no podrás recuperar este empleado",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				$.ajax({
					url:"/eliminar/" +id,
					success: function(res){
						console.log(res);
					}
				})
				swal("El empleado ha sido eliminado", {
					icon: "success",
				}).then((ok) => {
					if(ok){
					location.href="/listar";						
					}
				});
			} else {
				swal("Cancelado");
			}
		});
}