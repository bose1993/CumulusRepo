'use strict';

angular.module('cumulusApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
