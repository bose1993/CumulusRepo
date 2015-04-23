'use strict';

angular.module('testApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
