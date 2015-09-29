(function() {
	'use strict';

	var app = angular.module('imageGenerator', []);
	var image = [];
	var item_id = '1234567';
	var item_description = 'LEITE MOLICO DESN SEM LACTOSE LV 1L';
	var item_old_price = '4,99';
	var item_price = '3,99';

	app.controller('ImageController', ['$http', function($http) {
		var self = this;
		this.image = image;
		this.item_id = item_id;
		this.item_description = item_description;
		this.item_old_price = item_old_price;
		this.item_price = item_price;

		this.generateImagePreview = generateImagePreview;

		function generateImagePreview() {
			self.url_item_id = self.item_id;
			self.url_item_description = self.item_description;''
			self.url_item_old_price = self.item_old_price;
			self.url_item_price = self.item_price;
		}
	}]);
})();