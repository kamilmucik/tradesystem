;
(function($) {
	var es = {
		defaults : {
			spin : '0',
			zus : '0'
		},
		init : function(options) {
			var o = options, 
				settings = $.extend({}, 
				es.defaults, o), 
				spin = settings.spin,
				zus = settings.zus, 
				$this = $(this);
			
			_spin = ($("#xxx").attr("spin") == 0)?settings.spin:$("#xxx").attr("spin");
			_zus = ($("#xxx").attr("zus") == 0)?settings.zus:$("#xxx").attr("zus");
			
			_spin = parseInt(_spin);
			_zus = parseInt(_zus);
			
			kwota_na_fakturze = Math.ceil((_spin+(_zus*0.81))/0.81);
			
			$("#kwota-na-fakturze").val(kwota_na_fakturze);
			
			es.spin ();
			
		},
//		
		spin : function (options){	
			_spin = ($("#xxx").attr("spin") == 0)?settings.spin:$("#xxx").attr("spin");
			_netto = ($("#xxx").attr("netto") == 0)?0:$("#xxx").attr("netto");
			_zus = ($("#xxx").attr("zus") == 0)?0:$("#xxx").attr("zus");
			_urlop = ($("#xxx").attr("urlop") == 0)?0:$("#xxx").attr("urlop");
			__urlop = 0;
			
			if (_urlop == "1"){
				__urlop = (_spin / 168) * 8 * 2.1;
			}
			console.log("_urlop: " + _urlop + " : " + __urlop);
			
			_netto = parseInt(_netto);
			_zus = parseInt(_zus);
			
			_podstawa_opodatkowania = _netto - _zus;
			$("#podstawa-opodatkowania").html(_podstawa_opodatkowania);
			
			_podatek_19 = Math.ceil(_podstawa_opodatkowania * 0.19);
			$("#podatek-19").html(_podatek_19);
			
			_kwota_brutto_na_fakturze = Math.ceil((_netto * 0.23) + _netto + __urlop);
			$("#kwota-brutto-na-fakturze").html(_kwota_brutto_na_fakturze);
			
			 
		},
	};
	$.fn.es = function(method, options) {
		if (es[method]) {
			return es[method].apply(this, Array.prototype.slice.call(arguments,
					1));
		} else if (typeof method === 'object' || !method) {
			return es.init.apply(this, arguments);
		} else {
			$.error('Method ' + method + ' does not exist');
		}
	}
})(jQuery);