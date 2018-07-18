var CosUtil = {
    bucket: 'biminds-1253641254',
    region: 'ap-beijing',
    secretKey: 'ZxlmdIHZfYFk3bfcUEKNI6mKrHamqphU',
    secretId: 'AKID2ub5ZvM7vXw9D7jxLXXLxNhkbHl3p4rT',
    protocol: location.protocol === 'https:' ? 'https:' : 'http:',

// 计算签名
getAuthorization: function (options, callback) {
    $.get('http://example.com/server/sts.php', {
        bucket: options.Bucket,
        region: options.Region,
    }, function (data) {
        callback({
            TmpSecretId: data.TmpSecretId,
            TmpSecretKey: data.TmpSecretKey,
            XCosSecurityToken: data.XCosSecurityToken,
            ExpiredTime: data.ExpiredTime,
        });
    });
}
,
}