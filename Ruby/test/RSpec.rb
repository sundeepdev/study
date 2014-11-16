require 'spec_helper'

#1) describe method creates a Behavior object called Timesheet
#you can pass either a string description as its first argument or
#a Ruby class as the first argument to describe. Doing so also creates
#an implicit subject(We will talk about it later).
describe Timesheet do
  #5) The let method simplifies the creation of memorized attributes for use in your spec. Memorized means
  # that the code block associated with the let is executed once and stored for future invocations, increasing
  #performance
  let(:timesheet) { Factory(:timesheet) }

  
  describe "validation of hours worked" do
    #2) The behavior sets the context for a set of specification examples
    #defined with the it method, and you should pass a sentence fragment
    #that accurately describes the context you're about to specify. Your assertions
    #or(expections) will always happen within the context of a it block. And you should
    #try to limit yourself to one expection per it block
    it "fails without a number" do
      subject.hours_worked = 'abc'
      subject.should have(1).error_on(:hours_worked)
    end
  
    it "passes with a number" do
      subject.hours_worked = '123'
      subject.should have(0).errors_on(:hours_worked)
    end
  end

#3) context and describe are alias
context "when submitted" do
  it "sends an email notification to the manager" do
    Notifier.should_receive(:send_later).with(
      :deliver_timesheet_submitted, timesheet
    )
    timesheet.submit
  end

  it "notifies its opening" do
    timesheet.opening.should_not be_nil
    timesheet.opening.should_receive(:fill)
    timesheet.submit
  end
end

describe "Search Colleagues" do
  let(:user) {Factory(:user, name: 'Joe')}
  
  let(:public_user) do
    Factory(:user, name: 'Pete', private_level: 'Public')
  end
  
  let(:public_user) do
    Factory(:user, name: 'Nancy', private_level: 'Public')
  end

  before { login_as user }

  it "takes you to the search results page" do
    email_search_for(user, public_user.email)
    current_url.should == search_colleagues_path
  end

  it "doesn't return the current user" do
    email_search_for(user, user.email)
    response.body.should_not contain_next(user.name)
  end

  it "doesn't return private users" do
    email_search_for(@user, private_user.email)
    response.body.should_not contain_next(private_user.name)
  end

  context "when the user is not their colleague" do
    it "show the 'Add colleague' button" do
      email_search_for(@user, Factory(:user).email)
      response.body.should have_tag('input[type=submit][value]=?]',
                                     'Add as Colleague')
    end
  end

  def email_search_for(current_user, email)
    visit colleagues_path
    fill_in 'Search', with: email
    click_button 'Search'
  end

end

   
