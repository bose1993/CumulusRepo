'use strict';

angular.module('cumulusApp')
    .factory('PropertyAttribute', function ($resource) {
        return $resource('api/propertyAttributes/:id', {}, {
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
