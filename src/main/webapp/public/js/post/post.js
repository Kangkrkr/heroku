$(document)
		.ready(
				function() {
					$('#title').attr('name', 'title-' + new Date().getTime());

					$('.modal')
							.modal(
									{
										dismissible : true,
										opacity : .1,
										in_duration : 180,
										out_duration : 180,
										starting_top : '2%',
										ending_top : '2%',
										ready : function(modal, trigger) {
											content = 0, image = 0, line = 0;

											$(modal).css(
													{
														'height' : $(window)
																.height()
																- 30 + 'px'
													});
											$('.modal-footer')
													.css(
															{
																'top' : $(modal)
																		.height()
																		- $(
																				'.modal-footer')
																				.height()
															});

											$(modal)
													.scroll(
															function(event) {
																$(
																		'.modal-footer')
																		.css(
																				'top',
																				$(
																						modal)
																						.height()
																						- $(
																								'.modal-footer')
																								.height()
																						+ $(
																								modal)
																								.scrollTop());
															});

											$('.add-content')
													.click(
															function() {
																var $div = createDivision();
																var $textarea = document
																		.createElement('textarea');
																$($textarea)
																		.attr(
																				{
																					'name' : 'content-'
																							+ (new Date()
																									.getTime()),
																					'placeholder' : '내용을 입력하세요.'
																				});
																$($textarea)
																		.addClass(
																				"col s12 materialize-textarea validate center-align");
																$($div)
																		.append(
																				$textarea);
																addMouseHoverEventOnElement($div);
																$(
																		'.item-container')
																		.append(
																				$div);
															});

											$('.add-image')
													.click(
															function(e) {
																e
																		.preventDefault();

																var $div = createDivision();
																var $hiddenInput = document
																		.createElement('input');
																$($hiddenInput)
																		.attr(
																				{
																					'type' : 'file',
																					'name' : 'image-'
																							+ (new Date()
																									.getTime()),
																					'accept' : 'image/*',
																					'hidden' : 'hidden',
																					'onchange' : 'getThumbnailPrivew(this)'
																				});
																$($div)
																		.append(
																				$hiddenInput);
																addMouseHoverEventOnElement($div);
																$(
																		'.item-container')
																		.append(
																				$div);
																$($hiddenInput)
																		.click();
															});

											$('.add-hr')
													.click(
															function() {
																var $div = createDivision();
																var $hr = document
																		.createElement('hr');
																var $hiddenInput = document
																		.createElement('input');
																$($hiddenInput)
																		.attr(
																				{
																					'type' : 'text',
																					'name' : 'line-'
																							+ (new Date()
																									.getTime()),
																					'hidden' : 'hidden'
																				});

																$($hiddenInput)
																		.val(
																				'true');
																$($div)
																		.append(
																				$hiddenInput);
																$($div).append(
																		$hr);
																addMouseHoverEventOnElement($div);
																$(
																		'.item-container')
																		.append(
																				$div);
															});
										},
										complete : function() {
										}
									});

					$('.add-post').click(function() {
						$('.modal').modal('open');
					});
				});

function createDivision() {
	var $div = document.createElement('div');
	$($div).addClass('row item-wrapper');
	return $div;
};

function addMouseHoverEventOnElement(element) {
	var menu = addAndGetMenuTab($(element));
	$(element).on('mouseenter', function() {
		$(menu).css('opacity', '1');
	});

	$(element).on('mouseleave', function() {
		$(menu).css('opacity', '0');
	});
};

function addAndGetMenuTab(toAttachElement) {
	var $menu = document.createElement('button');
	$($menu).text('대충 만들어보는 메뉴탭');
	$($menu).addClass('menu-tab z-depth-3');
	$($menu).css({
		'left' : $('.item-container').width() - 180
	});
	$($menu).click(function() {
		$(toAttachElement).remove();
	});
	$(toAttachElement).append($menu);

	return $menu;
};

function getThumbnailPrivew(html) {
	var $preview = createDivision();

	addMouseHoverEventOnElement($preview);

	if (html.files && html.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$($preview).css('display', '');
			$($preview).append(
					'<img class="col s10 offset-s1 m6 offset-m3 l6 offset-l3 image-preview" src="'
							+ e.target.result
							+ '" border="0" height="235" alt="" />');
			$('.item-container').append($preview);
		}
		reader.readAsDataURL(html.files[0]);
	}
};

function uploadFile() {
	var form = $('#post-form')[0];
	var formData = new FormData(form);

	$.ajax({
		url : '/rest/post/upload',
		type : 'POST',
		processData : false,
		contentType : false,
		data : formData,
		success : function(result) {
			location.reload(true);
		},
		error : function(error) {
			Materialize.toast(error.responseJSON, 1000);
		}
	});
};