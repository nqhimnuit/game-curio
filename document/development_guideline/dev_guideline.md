# Convention:

## Issue types and naming prefix
- bug: ISSUE-
- curio spec related: CURIO-
- knowledge topic: LEARN-

## Working with tickets
- Ticket id start with "<ISSUE_TYPE>-<number_of_issue_in_url>" (see: https://github.com/nqhimnuit/game-curio/issues/19)
- (need to check) when finish fixing the issue, squash the commit FIRST then add to commit message: fix <issue_number> (or reference: https://stackoverflow.com/a/4529172)

## Convert Issue to Pull request
1. create new branch
```
$ git checkout -b <BRANCH-ID>
```

2. work on new branch, push to repo
```
$ git push -u origin <BRANCH-ID>
```

3. create pull request by:
```
$ curl --user "nqhimnuit" --request POST --data '{"issue": <ID>, "head": "nqhimnuit:<BRANCH-ID>", "base": "master"}' https://api.github.com/repos/nqhimnuit/game-curio/pulls
```
