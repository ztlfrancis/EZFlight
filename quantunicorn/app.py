from flask import Flask,request, render_template
import json
from flask_cors import CORS

app = Flask(__name__)
CORS(app)


@app.route('/')
def capflow():
    rus = []
    for i in range(3):
        rus.append(ru("BMY",39,"Healthcare","Drug Manufacturers-General"))
    rus.append(ru("AAPL", 5, "Technology", "Consumer Electronics"))
    return render_template('dataflow.html',ru=rus)
@app.route('/index')
def index():
    return render_template('index2.html')
@app.route('/indicator')
def indicator():
    return render_template('indicator.html')
@app.route('/joinus')
def joinus():
    return render_template('joinus.html')
@app.route('/newqu')
def newqu():
    return render_template('newTop100.html')
@app.route('/useterm')
def useterm():
    return render_template('useterm.html')
@app.route('/qu100')
def qu100():
    rus = []
    for i in range(3):
        rus.append(ru("BMY",39,"Healthcare","Drug Manufacturers-General"))
    rus.append(ru("AAPL", 5, "Technology", "Consumer Electronics"))
    return render_template('mutiflow.html',ru=rus)
@app.route('/test')
def test():
    testRes = {"data":[{"near":51.10499474688162,"total":29.48634815971056,"xlabel":"9"},{"near":76.24999973396531,"total":30.911458814112567,"xlabel":"10"},{"near":28.160386022701093,"total":0.6152411718364191,"xlabel":"11"},{"near":61.33878844896408,"total":19.980329106870574,"xlabel":"12"},{"near":100.0,"total":16.265821127528568,"xlabel":"13"},{"near":36.449590749718574,"total":0.019631625796506215,"xlabel":"14"},{"near":44.22182386823126,"total":-7.7196639941132315,"xlabel":"15"},{"near":0.0,"total":0.0,"xlabel":"16"}],"message":"Last updated on 2021-01-11 22:34 Eastern Time\n\u5927\u76d8\u8d44\u91d1\u6d41\u6307\u6807\uff08+\u4e3a\u957f\u671f\u77ed\u671f\u8d44\u91d1\u6d41\u90fd\u4e3a\u6d41\u5165\u7684\u6807\u7684\uff0c-\u4e3a\u957f\u671f\u77ed\u671f\u8d44\u91d1\u6d41\u90fd\u4e3a\u6d41\u51fa\u7684\u6807\u7684\uff0cN\u4e3a\u957f\u671f\u77ed\u671f\u8d44\u91d1\u5e03\u5c40\u4e0d\u4e00\u81f4\u7684\u6807\u7684\uff09\nSPY(+) QQQ(N) IWM(N) DIA(+) \n\u4e2a\u80a1\u6392\u540d\uff08\u8d44\u91d1\u6d41\u4e3aN\u7684\u6807\u7684\u4e0d\u8ba1\u5165\u6b64\u6392\u540d\uff09\nPositive Top 10: SPY, AAPL, BABA, ZM, DIS, TSLA, MRNA, JD, SLV, GDX\nNegative Bottom 10: ECL, DISH, XLV, PTON, CZR, SBUX, JNJ, XLK, W, NFLX\n\u5386\u53f2\u6570\u636e\u56de\u6d4b\u7ed3\u679c\nYour choice: AAPL since 2020-01-14\nAAPL was in top-20 202 times and got higher 169 times within 1 day(s), 83.66%. It went 1.94% higher on average\nAAPL was in top-100 220 times and got higher 186 times within 1 day(s), 84.55%. It went 1.92% higher on average\nAAPL was in top-200 221 times and got higher 187 times within 1 day(s), 84.62%. It went 1.93% higher on average\nYour choice on 2020-05-18: AAPL(+) 2/1724\nYour choice on 2020-05-15: AAPL(+) 1/1577\nYour choice on 2020-05-14: AAPL(+) 7/1541\nYour choice on 2020-05-13: AAPL(+) 4/1614\nYour choice on 2020-05-12: AAPL(+) 2/1558\n"};
    return json.dumps(testRes)
@app.route('/testMoney')
def testMoney():
    testRes = {'message': 'Success\nLast updated on 2021-05-18 17:03 Eastern Time',
               'data': [{'rank': 1, 'ticker': 'AAPL', 'change': -5, 'industry': 'tech', 'sector': 'consumer tech'},
                      {'rank': 1, 'ticker': 'AAPL', 'change': 5, 'industry': 'tech', 'sector': 'consumer tech'},
                      {'rank': 1, 'ticker': 'AAPL', 'change': 0, 'industry': 'tech', 'sector': 'consumer tech'}]};
    return json.dumps(testRes)

if __name__ == '__main__':
    app.run()
class ru(object):
    def __init__(self, Ticker, change,Sector,Industry):
        self.Ticker = Ticker
        self.change = change
        self.Sector = Sector
        self.Industry = Industry

