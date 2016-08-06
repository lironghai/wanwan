(function() {
"use strict";
function factory(angular, $) {
	var namespace = "/setting/contractSetting";
	/** 定义的service，会被注册为ContractSettingService */
	var service = function($rootScope, $http, $q, $timeout) {
		var app = $rootScope.app;	// config中配置的window.app
		var session = $rootScope.session;
		var dicts = { };	// 按type-code缓存的数据
		var cache = { };	// 按id缓存的参数
		var cachedType = { };	// 以缓存的type的所有配置
		return {
			/** 构造此模块下的路径 */
			url: function(url, ns) {
				if (!ns) ns = namespace;
				if (url.charAt('0') != '/') return url;	// 相对路径
				return app.url(ns + url);
			},
			/**
			 * 添加数据到缓存中
			 */
			put: function(data) {
				if (!data) return;
				if (angular.isArray(data)) {
					var $this = this;
					angular.forEach(data, function(dict) {
						$this.put(dict);
					});
					return;
				}
				
				var dict = dicts[ data.type ];
				if (!dict) dict = dicts[ data.type ] = { };
				dict[ data.code ] = data;
				cache[ data.id ] = data;
			},
			
			/** 得到当前菜单下的固定参数 */
			getCurrentParams: function() {
				return session.currentMenu ? session.currentMenu.params : null;
			},
			/**
			 * 查询方法
			 * @param params 查询条件,可以为null.不为null时,其中最好不要包含分页信息
			 * @param pageRequest 分页参数,至少包含pageNumber和pageSize两个字段,省略则视为不分页
			 * @param ns 当前的namespace,主要用于controller中传入
			 */
			query: function(params, pageRequest, ns) {
				var deferred = $q.defer();
				var args = $.extend({ }, params || { }, pageRequest || { });
				var $this = this;
				$http.post(this.url("/query.do", ns), args).success(function(page) {
					$this.put(page.content);
					deferred.resolve(page);
				});
				return deferred.promise;
			},
			/**
			 * 保存参数
			 * @param dept 要保存参数，ID为null则表示新增，否则表示修改
			 * @param ns 当前的namespace,主要于controller中传入
			 */
			save: function(contractSetting, ns) {
				var deferred = $q.defer();
				var $this = this;
				$http.post(this.url("/save.do", ns), { entity: contractSetting }).success(function(contractSetting) {
					$this.put(contractSetting);
					deferred.resolve(contractSetting);
				});
				return deferred.promise;
			},
			
			/**
			 * 删除参数
			 * @param ids 要删除的id数组
			 * @param ns 当前的namespace,主要用于controller中传入
			 */
			remove: function(ids, ns) {
				var deferred = $q.defer();
				$http.post(this.url("/remove.do", ns), { ids: ids } ).success(function(ids) {
					deferred.resolve(ids);
				});
				return deferred.promise;
			},
			
			/** 
			 * 查询具体的参数
			 * @param type 参数类型,必须.如果想省略此参数,则应当使用query方法进行查询
			 * @param code 参数值,可以省略,则查询出所有type下的数据
			 * @param params 附加参数,如果未指定status,则默认会加上status=1的条件
			 */
			get: function(type, code, params) {
				var deferred = $q.defer();
				var args = { type: type, status: app.status.valid };	// 只查询有效的数据
				var typeDicts = dicts[ type ];
				if (code) {	// 读取缓存数据
					if (typeDicts) {
						var dict = typeDicts[ code ];
						if (dict) { // 数据已经存在
							$timeout(function() { deferred.resolve(dict); }, 100);
							return deferred.promise;
						}
					}
					args.code = code;
				} else if (cachedType[ type ]){
					$timeout(function() { deferred.resolve(typeDicts); }, 100);
					return deferred.promise;
				}
				// 如果未指定code则不读取缓存数据
				if (params) args = $.extend(args, params);
				var $this = this;
				$http.post(this.url("/get.do"), args).success(function(data) {
					$this.put(data);
					if (!code) {
						typeDicts = dicts[ type ];
						if (typeDicts) cachedType[ type ] = true;	// 表示此类型已经处理,下次不需要重新读取数据库
					}
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	}
	service.$inject = [ "$rootScope", "$http", "$q", "$timeout" ];
	service.$name = "ContractSettingService"; // 可以省略，系统会默认以"模块名+Service"进行注册.如果此模块可能被其他模块依赖,则不能省略

	/** 定义的controller，会被注册为ContractSettingController */
	var controller = function($scope, $rootScope, $window, ContractSettingService, LocaleService) {
		var current = $rootScope.current;
		var session = $rootScope.session;
		$.extend($scope, {
			
			/**
			 * 查询方法
			 * @param params 查询条件,可以为null,则默认为rootScope.current.params
			 * @param pageRequest 分页参数,至少包含pageNumber和pageSize两个字段,可以省略则默认为rootScope.current.pageRequest
			 */
			query: function(params, pageRequest) {
				params = params || current.params;
				pageRequest = pageRequest || current.pageRequest;
				params = $.extend(params || { }, ContractSettingService.getCurrentParams() || { });
				ContractSettingService.query(params, pageRequest, session.currentMenu.namespace).then(function(page) {
					current.page = page;
				});
				current.operator = null;
			},
			
			/** 新增 */
			add: function() {
				// var entity = { isNew: function() { return true; } };	// 新增数据添加一个isNew的function
				current.entity = $.extend({ isNew: function() { return true; } }, ContractSettingService.getCurrentParams());
				current.operator = "add";
			},
			
			/** 查看 */
			view: function(contractSetting) {
				current.operator = "view";
				current.entity = contractSetting;
			},

			/** 编辑 */
			edit: function(contractSetting) {
				current.operator = "edit";
				if (!contractSetting) contractSetting = current.entity;
				current.entity = $.extend({ }, contractSetting);
			},
			
			/** 取消新建/编辑操作，查看数据 */
			cancel: function() {
				if (current.operator == 'edit') {
					current.operator = 'view';
					$.each(current.page.content, function(idx, user) {
						if (current.entity.id == user.id) {
							$scope.view(user);
						}
					});
				} else if (current.operator == 'add') {
					current.operator = null;
				} else {
					current.operator = null;
				}
			},
			
			/** 保存数据 */
			save: function(contractSetting) {
				if (!current.operator) return;
				ContractSettingService.save(contractSetting || current.entity, session.currentMenu.namespace).then(function(entity) {
					var message =  LocaleService.getText('app.update.success');
					alert(message);
					view(entity);
				});
			},
			
			/** 
			 * 删除数据,delete为JavaScript的关键字，所以方法命名为remove
			 * @param depts Array/Object，要删除的数组或对象，可以省略，则表示删除当前dept 
			 */
			remove: function(contractSetting) {
				if (!$window.confirm(LocaleService.getText("app.confirm.delete"))) return;
				if (!contractSetting) contractSetting = [ current.entity ];
				if (!angular.isArray(contractSetting)) contractSetting = [ contractSetting ];
				var ids = [ ];
				
				var message = "";
				angular.forEach(contractSetting, function(contractSetting) {
					ids.push(contractSetting.id);
				});
				if (ids.length < 1){
					message = LocaleService.getText('app.delete.failure');
					alert(message);
					return;
				}
				ContractSettingService.remove(ids, session.currentMenu.namespace).then(function(contractSetting) {
					message = LocaleService.getText('app.delete.success');
					alert(message);
					$scope.query();
				});
			}
		});
	};
	controller.$inject = [ "$scope", "$rootScope", "$window", "ContractSettingService", "LocaleService"];
	controller.$name = "ContractSettingController"; // 可以省略，系统会默认以"模块名+Controller"进行注册

	var module = {
		service : service,
		controller : controller
	};

	return module;
}

define("ContractSetting", [ "angular", "jquery" ], function(angular, $) {
	return factory(angular, $);
});

})();