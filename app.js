(function() {
    'use strict';
    angular.module('Authentication', []);
    angular.module('Home', []);

    angular
        .module('plunker', ['ngRoute',
            'Authentication',
            'Home',
            'ngRoute',
            'ngCookies','ngMessages'
        ])
        .config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider'];

    function moduleConfig ($routeProvider) {

        $routeProvider
            .when('/create', {
                templateUrl: 'create.tmpl.html',
                controller: 'NewController',
                controllerAs: 'newVm'
            })
            .when('/update', {
                templateUrl: 'update.tmpl.html',
                controller: 'ChangeController',
                controllerAs: 'changeVm'
            })
            .when('/getAll', {
                templateUrl: 'getAll.tmpl.html',
                controller: 'ReservationsController',
                controllerAs: 'rsvVm'
            })
    }

})();
