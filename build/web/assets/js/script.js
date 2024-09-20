/*
Author       : Dreamguys
Template Name: Mentoring - Bootstrap Template
Version      : 1.0
*/

(function($) {
    "use strict";
		
	if($('.toggle-password').length > 0) {
		$(document).on('click', '.toggle-password', function() {
			$(this).toggleClass("fa-eye fa-eye-slash");
			var input = $(".pass-input");
			if (input.attr("type") == "password") {
				input.attr("type", "text");
			} else {
				input.attr("type", "password");
			}
		});
	}


	// Stick Sidebar
	
	if ($(window).width() > 767) {
		if($('.theiaStickySidebar').length > 0) {
			$('.theiaStickySidebar').theiaStickySidebar({
			  // Settings
			  additionalMarginTop: 30
			});
		}
	}
	
	// Sidebar
	
	if($(window).width() <= 991){
	var Sidemenu = function() {
		this.$menuItem = $('.main-nav a');
	};
	
	function init() {
		var $this = Sidemenu;
		$('.main-nav a').on('click', function(e) {
			if($(this).parent().hasClass('has-submenu')) {
				e.preventDefault();
			}
			if(!$(this).hasClass('submenu')) {
				$('ul', $(this).parents('ul:first')).slideUp(350);
				$('a', $(this).parents('ul:first')).removeClass('submenu');
				$(this).next('ul').slideDown(350);
				$(this).addClass('submenu');
			} else if($(this).hasClass('submenu')) {
				$(this).removeClass('submenu');
				$(this).next('ul').slideUp(350);
			}
		});
	}

	// Sidebar Initiate
	init();
	}
	
	// Select 2
	
	if($('.select').length > 0) {
		$('.select').select2({
			minimumResultsForSearch: -1,
			width: '100%'
		});
	}
	
	// Date Time Picker
	
	if($('.datetimepicker').length > 0) {
		$('.datetimepicker').datetimepicker({
			format: 'DD/MM/YYYY',
			icons: {
				up: "fas fa-chevron-up",
				down: "fas fa-chevron-down",
				next: 'fas fa-chevron-right',
				previous: 'fas fa-chevron-left'
			}
		});
	}
	
	// Floating Label

	if($('.floating').length > 0 ){
		$('.floating').on('focus blur', function (e) {
		$(this).parents('.form-focus').toggleClass('focused', (e.type === 'focus' || this.value.length > 0));
		}).trigger('blur');
	}
	
	// Mobile menu sidebar overlay
	
	$('.header-fixed').append('<div class="sidebar-overlay"></div>');
	$(document).on('click', '#mobile_btn', function() {
		$('main-wrapper').toggleClass('slide-nav');
		$('.sidebar-overlay').toggleClass('opened');
		$('html').addClass('menu-opened');
		return false;
	});
	
	$(document).on('click', '.sidebar-overlay', function() {
		$('html').removeClass('menu-opened');
		$(this).removeClass('opened');
		$('main-wrapper').removeClass('slide-nav');
	});
	
	$(document).on('click', '#menu_close', function() {
		$('html').removeClass('menu-opened');
		$('.sidebar-overlay').removeClass('opened');
		$('main-wrapper').removeClass('slide-nav');
	});

	// Header Eight

	$(window).scroll(function(){
	var scroll = $(window).scrollTop();
		if (scroll > 70) {
			$(".header-eight").addClass("sticky");
		}
		else{
			$(".header-eight").removeClass("sticky");  	
		}
	})
	
	// Tooltip
	
	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
	var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	  return new bootstrap.Tooltip(tooltipTriggerEl)
	})

	// Home popular mentor slider

	if($('.owl-carousel.mentoring-course').length > 0 ){
		var owl = $('.owl-carousel.mentoring-course');
	      	owl.owlCarousel({
	        margin: 30,
	        nav : false,
	        nav: true,
	        loop: true,
	        responsive: {
	          	0: {
	            	items: 1
	          	},
	          	768 : {
	            	items: 3
	          	},
	          	1170: {
	            	items: 4
	          	}
	        }
	    });
    }

    // Popular course two

	if($('.owl-carousel.popular-course-slide-two').length > 0 ){
		var owl = $('.owl-carousel.popular-course-slide-two');
	      	owl.owlCarousel({
	        margin: 30,
	        nav : false,
	        nav: true,
	        loop: true,
	        responsive: {
	          	0: {
	            	items: 1
	          	},
	          	768 : {
	            	items: 2
	          	},
	          	1170: {
	            	items: 3
	          	}
	        }
	    });
    }

	// Slick testimonial two

	if($('.say-about.slider-for').length > 0) {
		$('.say-about.slider-for').slick({
			slidesToShow: 1,
			slidesToScroll: 1,
			arrows: true,
			fade: true,
			asNavFor: '.client-img.slider-nav'
		});																										
	}

	if($('.client-img.slider-nav').length > 0) {
		$('.client-img.slider-nav').slick({
			slidesToShow: 5,
			slidesToScroll: 1,
			asNavFor: '.say-about.slider-for',
			dots: true,
			arrows: false,
			centerMode: true,
			focusOnSelect: true
			
		});
	}

	// Slick testimonial three

	if($('.mentor-testimonial.lazy').length > 0) {
		$(".mentor-testimonial.lazy").slick({
			lazyLoad: 'ondemand',
			infinite: true
		});
	}

	// JQuery counterUp

	if($('.strength-list .counterUp').length > 0) {
		$('.strength-list .counterUp').counterUp({
            delay: 15,
            time: 1500
        });
	}

	// Home four header

	$(window).scroll(function(){
		  var sticky = $('.scroll-sticky'),
			  scroll = $(window).scrollTop();

		  if (scroll >= 100) sticky.addClass('add-header-bg');
		  else sticky.removeClass('add-header-bg');
	});

	// Home three header

	$(window).scroll(function(){
		  var sticky = $('.scroll-sticky-three'),
			  scroll = $(window).scrollTop();

		  if (scroll >= 100) sticky.addClass('add-header-three');
		  else sticky.removeClass('add-header-three');
	});

	// Back to top

	if ($('.back-to-top-icon').length > 0) {
		var scrollTrigger = 100, // px
			backToTop = function () {
				var scrollTop = $(window).scrollTop();
				if (scrollTop > scrollTrigger) {
					$('.back-to-top-icon').addClass('show');
				} else {
					$('.back-to-top-icon').removeClass('show');
				}
			};
		backToTop();
		$(window).on('scroll', function () {
			backToTop();
		});
		$('.back-to-top-icon').on('click', function (e) {
			e.preventDefault();
			$('html,body').animate({
				scrollTop: 0
			}, 700);
		});
	}

	// Fade in scroll

	if($('.main-wrapper .aos').length>0){
		AOS.init({
			duration:1200,
			once:true
		});
	}

	// Add More Hours
	
    $(".hours-info").on('click','.trash', function () {
		$(this).closest('.hours-cont').remove();
		return false;
    });

    $(".add-hours").on('click', function () {
		
		var hourscontent = '<div class="row form-row hours-cont">' +
			'<div class="col-12 col-md-10">' +
				'<div class="row form-row">' +
					'<div class="col-12 col-md-6">' +
						'<div class="form-group">' +
							'<label>Start Time</label>' +
							'<select class="form-control form-select">' +
								'<option>Select</option>' +
								'<option>12.00 am</option>' +
								'<option>1.00 am</option>' + 
								'<option>2.00 am</option>' +
								'<option>3.00 am</option>' +
								'<option>4.00 am</option>' +
								'<option>5.00 am</option>' +
								'<option>6.00 am</option>' +
								'<option>7.00 am</option>' +
								'<option>8.00 am</option>' +
								'<option>9.00 am</option>' +
								'<option>10.00 am</option>' +
								'<option>11.00 am</option>' +
								'<option>1.00 pm</option>' + 
								'<option>2.00 pm</option>' + 
								'<option>3.00 pm</option>' + 
								'<option>4.00 pm</option>' + 
								'<option>5.00 pm</option>' + 
								'<option>6.00 pm</option>' + 
								'<option>7.00 pm</option>' + 
								'<option>8.00 pm</option>' + 
								'<option>9.00 pm</option>' + 
								'<option>10.00 pm</option>' + 
								'<option>11.00 pm</option>' + 
							'</select>' +
						'</div>' +
					'</div>' +
					'<div class="col-12 col-md-6">' +
						'<div class="form-group">' +
							'<label>End Time</label>' +
							'<select class="form-control form-select">' +
								'<option>Select</option>' +
								'<option>12.00 am</option>' +
								'<option>1.00 am</option>' + 
								'<option>2.00 am</option>' +
								'<option>3.00 am</option>' +
								'<option>4.00 am</option>' +
								'<option>5.00 am</option>' +
								'<option>6.00 am</option>' +
								'<option>7.00 am</option>' +
								'<option>8.00 am</option>' +
								'<option>9.00 am</option>' +
								'<option>10.00 am</option>' +
								'<option>11.00 am</option>' +
								'<option>1.00 pm</option>' + 
								'<option>2.00 pm</option>' + 
								'<option>3.00 pm</option>' + 
								'<option>4.00 pm</option>' + 
								'<option>5.00 pm</option>' + 
								'<option>6.00 pm</option>' + 
								'<option>7.00 pm</option>' + 
								'<option>8.00 pm</option>' + 
								'<option>9.00 pm</option>' + 
								'<option>10.00 pm</option>' + 
								'<option>11.00 pm</option>' + 
							'</select>' +
						'</div>' +
					'</div>' +
				'</div>' +
			'</div>' +
			'<div class="col-12 col-md-2"><label class="d-md-block d-sm-none d-none">&nbsp;</label><a href="#" class="btn btn-danger trash"><i class="far fa-trash-alt"></i></a></div>' +
		'</div>';
		
        $(".hours-info").append(hourscontent);
        return false;
    });
	
	// Content div min height set
	
	function resizeInnerDiv() {
		var height = $(window).height();	
		var header_height = $(".header").height();
		var footer_height = $(".footer").height();
		var setheight = height - header_height;
		var trueheight = setheight - footer_height;
		$(".content").css("min-height", trueheight);
	}
	
	if($('.content').length > 0 ){
		resizeInnerDiv();
	}

	$(window).resize(function(){
		if($('.content').length > 0 ){
			resizeInnerDiv();
		}

	});
	
	// Date Range Picker

	if($('.bookingrange').length > 0) {
		var start = moment().subtract(6, 'days');
		var end = moment();

		function booking_range(start, end) {
			$('.bookingrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
		}

		$('.bookingrange').daterangepicker({
			startDate: start,
			endDate: end,
			ranges: {
				'Today': [moment(), moment()],
				'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
				'Last 7 Days': [moment().subtract(6, 'days'), moment()],
				'Last 30 Days': [moment().subtract(29, 'days'), moment()],
				'This Month': [moment().startOf('month'), moment().endOf('month')],
				'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
			}
		}, booking_range);

		booking_range(start, end);
	}
	
	// Chat

	var chatAppTarget = $('.chat-window');
	(function() {
		if ($(window).width() > 991)
			chatAppTarget.removeClass('chat-slide');
		
		$(document).on("click",".chat-window .chat-users-list a.media",function () {
			if ($(window).width() <= 991) {
				chatAppTarget.addClass('chat-slide');
			}
			return false;
		});
		$(document).on("click","#back_user_list",function () {
			if ($(window).width() <= 991) {
				chatAppTarget.removeClass('chat-slide');
			}	
			return false;
		});
	})();
	
	// Preloader
	
	$(window).on('load', function () {
		if($('#loader').length > 0) {
			$('#loader').delay(350).fadeOut('slow');
			$('body').delay(350).css({ 'overflow': 'visible' });
		}
	})

	// Testimonial Slider

	if($('#customers-testimonials').length > 0) {
		$('#customers-testimonials').owlCarousel({
			nav: true,
			items: 1,
			autoplay: true,
			loop: true,
			autoplayTimeout: 5000,
			navText: ["<i class='fas fa-chevron-left owl-nav-left'></i>", "<i class='fas fa-chevron-right owl-nav-right'></i>"],
		});
	}

	// Slick Slider
	
    if ($('.mendor-slider').length > 0) {
	 $('.mendor-slider').slick({
		  dots: false,
          autoplay: false,
          infinite: true,
          variableWidth: false,
          slidesToShow: 4,
          slidesToScroll: 1,
		  swipeToSlide: true,
		  responsive: [
                {
                    breakpoint: 1024,
                    settings: {
                        slidesToShow: 3,
                        slidesToScroll: 1                        
                    }
                },
				{
                    breakpoint: 992,
                    settings: {
                        slidesToShow: 3,
                        slidesToScroll: 1
                    }
                },
                {
                    breakpoint: 768,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 1
                    }
                },
                {
                    breakpoint: 500,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1
                    }
                }
            ]
		});
	}
	
})(jQuery);

function CheckEmail(text) {
    var email = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    var spanEmail = document.getElementById('span_email');
    if (!email.test(text.value)) {
        spanEmail.innerHTML = 'Email không hợp lệ (email_123@gmail.com)';
        spanEmail.style.color = 'red';
        return false;
    }
    spanEmail.innerHTML = '';
    return true;
}

function CheckPassword(text) {
    var password = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
    var spanPassword = document.getElementById('span_password');
    if (!password.test(text.value)) {
        spanPassword.innerHTML = 'Mật khẩu không hợp lệ (Cần có ít nhất 8 ký tự bao gồm viết hoa và ký tự đặc biệt)!';
        spanPassword.style.color = 'red';
        return false;
    }
    spanPassword.innerHTML = '';
    return true;
}

//function CheckConfirmPassword(text) {
//    var password = document.getElementById('password').value;
//    var spanRePassword = document.getElementById('span_rePassword');
//    if (password != text.value) {
//        spanRePassword.innerHTML = 'Mật khẩu không khớp!';
//        spanRePassword.style.color = 'red';
//        return false;
//    }
//    spanRePassword.innerHTML = '';
//    return true;
//
//}

function CheckAccountName(text) {
    var accountname = /^(?!.*\.\.)(?!.*\.$)[^\W][\w.]{5,29}$/;
    var spanAccountName = document.getElementById('span_accountName');
    if (!accountname.test(text.value)) {
        spanAccountName.innerHTML = 'Tên tài khoản không khớp()<br><ul><li>Không chứa hai dấu chấm liên tiếp</li><li>Không kết thúc bằng một dấu chấm</li>' +
                '<li>Bắt đầu bằng một ký tự chữ hoặc số </li>' + '<li>Có độ dài từ 5 đến 30 ký tự.</li>' + '<li>Chỉ chứa các ký tự chữ, số, dấu gạch dưới (_), hoặc dấu chấm (.).</li>' + '</ul>';
        spanAccountName.style.color = 'red';
        return false;
    }
    spanAccountName.innerHTML = '';

    return true;
}

function CheckPhoneNumber(text) {
    var phone_number = /^0\d{9}$/;
    var spanPhoneNumber = document.getElementById('span_phoneNumber');
    if (!phone_number.test(text.value)) {
        spanPhoneNumber.innerHTML = 'Số điện thoại không hợp lệ';
        spanPhoneNumber.style.color = 'red';
        return false;
    }
    spanPhoneNumber.innerHTML = '';
    return true;
}

function CheckFullName(text) {
    var full_name = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]{4,}(?:[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+){0,2}$/;
    var spanFullName = document.getElementById('span_fullname');
    if (!full_name.test(text.value)) {
        spanFullName.innerHTML = 'Họ tên không hợp lệ';
        spanFullName.style.color = 'red';
        return false;
    }
    spanFullName.innerHTML = '';
    return true;
}

function CheckDate(text) { 
    
    var span_date = document.getElementById('span_date');
    if(text.value.trim()===""){
        span_date.innerHTML = 'Vui lòng chọn ngày';
        span_date.style.color = 'red';
        return false;
    }
    span_date.innerHTML = '';
    return true;
}

function CheckAddress(text) {
    var address = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]{4,}(?:[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+){0,2}$/;
    var spanAddress = document.getElementById('span_address');
    if (!address.test(text.value)) {
        spanAddress.innerHTML = 'Địa chỉ không hợp lệ';
        spanAddress.style.color = 'red';
        return false;
    }
    spanAddress.innerHTML = '';
    return true;

}

//function CheckDate(text) { 
//    var datePattern = /^(0?[1-9]|[12][0-9]|3[01])\/(0?[1-9]|1[0-2])\/\d{4}$/;
//    var span_date = document.getElementById('span_date');
//    if(!datePattern.test(text.value)){
//        span_date.innerHTML = 'Ngày nhập không đúng(dd/MM/yyyy)';
//        span_date.style.color = 'red';
//        return false;
//    }
//    span_date.innerHTML = '';
//    return true;
//}



function validateForm() {

    if (!CheckEmail(document.getElementsByName("email")[0]) ||
            !CheckPassword(document.getElementsByName("password")[0]) ||
            !CheckConfirmPassword(document.getElementsByName("confirm_password")[0]) ||
            !CheckAccountName(document.getElementsByName("account_name")[0]) ||
            !CheckPhoneNumber(document.getElementsByName("phone")[0]) ||
            !CheckFullName(document.getElementsByName("full_name")[0]) ||
            !CheckAddress(document.getElementsByName("address")[0])|| !CheckDate(document.getElementsByName("birthday")[0])) {

        return false;
    }

    return true;
}

function validateSelection() {
    const roleSelect = document.getElementById("roleSelect");
    const errorMessage = document.getElementById("error-message");
    const selectedValue = roleSelect.value;

    if (selectedValue === "" || roleSelect.selectedIndex === 0) {
        errorMessage.innerHTML = 'Bạn chưa chọn role!';
        errorMessage.style.color = 'red';
        return false;
    } else {
        errorMessage.innerHTML = '';
        return true; // Trả về true nếu không có lỗi
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const roleSelect = document.getElementById("roleSelect");
    roleSelect.addEventListener("change", function () {
        const errorMessage = document.getElementById("error-message");
        errorMessage.innerHTML = '';
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const myForm = document.getElementById('myForm');

    myForm.addEventListener('submit', function (event) {
        if (!validateSelection()) {
            event.preventDefault(); // Ngăn chặn form gửi đi nếu có lỗi
        }
    });
});


