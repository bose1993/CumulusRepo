'use strict';

angular.module('cumulusApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


