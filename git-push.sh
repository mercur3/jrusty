#!/bin/sh

set -ex
cd $(dirname $0)

git push origin --all
git push origin --tags
git push --mirror https://gitlab.com/mercur3/jrusty.git

