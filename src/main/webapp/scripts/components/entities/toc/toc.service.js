'use strict';

angular.module('cumulusApp')
    .factory('Toc', function ($resource) {
        return $resource('api/tocs/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
